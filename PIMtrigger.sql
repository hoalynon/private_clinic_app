set serveroutput on;
alter session set nls_timestamp_format = 'DD/MM/YYYY HH24:MI:SS';
--------------------------------------------------------------------------------------------------------------
--TRIGGER BACSI
--1
create or replace trigger trg_bacsi_giatri
before insert or update
on BACSI
for each row
declare
    namPhucVu BACSI.NAMPHUCVU%type := :new.NAMPHUCVU;
    ngaySinh BACSI.NGAYSINH%type := :new.NGAYSINH;
begin
    if ngaySinh >= current_date then
        raise_application_error(-20001,'Ngay sinh khong hop le');
    elsif (namPhucVu < 0) or (extract(year from current_date) - extract(year from ngaySinh) < namPhucVu) 
            or (namPhucVu != round(namPhucVu)) then
        raise_application_error(-20002,'Nam phuc vu khong hop le');
    end if;
end;
/
--2
create or replace trigger trg_bacsi_benh
before update
on BACSI
for each row
declare
    khoa_old BACSI.tenkhoa%type := :old.tenkhoa;
    khoa_new BACSI.tenkhoa%type := :new.tenkhoa;
    cnt int;
    maBS_ BACSI.MaBS%type := :new.MaBS;
begin
    if khoa_old != khoa_new then
        select count(*) into cnt from CABENH cb where cb.mabs = maBS_ and tinhtrang != 'Da ket thuc';
        if cnt > 0 then
            raise_application_error(-20009,'Co ca benh bac si phu trach khong cung khoa');
        end if;
    end if;
end;
/
--3
create or replace trigger trg_bacsi_ngaysinh
before update
on BACSI
for each row
declare
    ngaySinh BACSI.NGAYSINH%type := :new.NGAYSINH;
    ngayBD CABENH.ngaybatdau%type;
    maBS_ BACSI.MaBS%type := :new.MaBS;
begin
    select min(ngaybatdau) into ngayBD from CABENH cb where cb.mabs = maBS_;
    if ngayBD is not null and trunc(ngayBD) <= ngaySinh then
        raise_application_error(-20011,'Ngay sinh phai som hon ngay bat dau ca benh phu trach');
    end if;
Exception 
    when NO_DATA_FOUND then
        dbms_output.put_line('');
end;
/
--TRIGGER BENHNHAN
--4
create or replace trigger trg_benhnhan_ngaysinh
before insert or update
on BENHNHAN
for each row
declare
    ngaysinh_ BENHNHAN.NGAYSINH%type := :new.NGAYSINH;
    mabn_ BENHNHAN.MaBN%type := :new.MaBN;
    ngaybatdau_ CABENH.ngaybatdau%type;
    cnt int;
begin
    if ngaysinh_ > current_date then
        raise_application_error(-20003,'Ngay sinh khong hop le');
    end if;
    
    select min(ngaybatdau) into ngaybatdau_ from CABENH where mabn = mabn_;
    if ngaybatdau_ is not null and trunc(ngaybatdau_) < ngaysinh_ then
        raise_application_error(-20012,'Ngay sinh phai som hon hoac bang ngay bat dau ca benh cua benh nhan');
    end if;
    
    select max(cb.ngaybatdau) into ngaybatdau_ from CABENH cb, BENH be where cb.mabn = mabn_ and cb.mabenh = be.mabenh and be.tenkhoa = 'Nhi' and cb.tinhtrang != 'Da ket thuc';
    if ngaybatdau_ is not null and (extract(year from ngaybatdau_) - extract(year from ngaysinh_)) > 15 then
        raise_application_error(-20016,'Benh nhan co ca benh thuoc khoa Nhi nen phai nho hon hoac bang 15 tuoi');
    end if;
exception 
    when NO_DATA_FOUND then
        dbms_output.put_line('');
end;
/
--TRIGGER BENH
--5
create or replace trigger trg_benh_giatri
for insert or update on BENH
compound trigger
    type array_record is record (tenbenh BENH.tenbenh%type, tenkhoa BENH.tenkhoa%type);
    type array_type is table of array_record index by pls_integer;
    array array_type;
    cnt int;
    ngaysinh_ BENHNHAN.ngaysinh%type;
    ngaybatdau_ CABENH.ngaybatdau%type;
    
    before each row is  
    begin
        if :new.tenbenh is null or :new.tenkhoa is null then 
            raise_application_error(-20036,'Ten benh va ten khoa khong duoc de trong');
        end if;
        array(array.count+1).tenkhoa := :new.tenkhoa;
        array(array.count).tenbenh := :new.tenbenh;
        --update ten khoa thanh Truyen Nhiem
        if :new.tenkhoa = 'Truyen Nhiem' then
            select count(*) into cnt from CABENH cb 
            where cb.mabenh = :new.mabenh
            and hinhthuc != 'Cach ly' and hinhthuc != 'Tai gia' and tinhtrang != 'Da ket thuc';
            if cnt > 0 then
                raise_application_error(-20022,'Ton tai ca benh truyen nhiem co hinh thuc khac cach ly hoac tai gia');
            end if;
        elsif :new.tenkhoa = 'Nhi' then
            for c in (select distinct mabn from CABENH where mabenh = :new.mabenh and tinhtrang != 'Da ket thuc') loop
                select ngaysinh into ngaysinh_ from BENHNHAN where mabn = c.mabn;
                select max(ngaybatdau) into ngaybatdau_ from CABENH cb where mabn = c.mabn and tinhtrang != 'Da ket thuc' and mabenh = :new.mabenh;
                if ngaybatdau_ is not null and extract(year from(ngaybatdau_)) - extract(year from(ngaysinh_)) > 15 then
                    raise_application_error(-20015,'Benh nhan co benh thuoc khoa Nhi tuoi phai nho hon hoac bang 15');
                end if;
            end loop;
        end if;
    end before each row;
    
    after statement is 
    begin
        for i in 1..array.count
        loop
            select count(*) into cnt from BENH be where be.tenbenh = array(i).tenbenh and be.tenkhoa = array(i).tenkhoa;
            if cnt >= 2 then 
                raise_application_error(-20018,'Khong duoc ton tai hai benh cung ten va cung khoa');
            end if;
        end loop;
    end after statement;
end;
/
--6
create or replace trigger trg_benh_bacsi
before update
on BENH
for each row
declare
    khoa_old BENH.tenkhoa%type := :old.tenkhoa;
    khoa_new BENH.tenkhoa%type := :new.tenkhoa;
    cnt int;
    mabenh_ BENH.mabenh%type := :new.mabenh;
begin
    if khoa_old != khoa_new then
        select count(*) into cnt from CABENH cb where cb.mabenh = mabenh_ and tinhtrang != 'Da ket thuc';
        if cnt > 0 then
            raise_application_error(-20010,'Co ca benh duoc phu trach boi bac si khac khoa');
        end if;
    end if;
end;
/
--TRIGGER CABENH
--7
create or replace trigger trg_cabenh_khoaTruyenNhiem
for insert or update on CABENH
compound trigger
    type array_record is record (mabn CABENH.mabn%type, hinhthuc CABENH.hinhthuc%type, tenkhoa BENH.tenkhoa%type,loaiphong PHONGBENH.loai%type);
    type array_type is table of array_record index by pls_integer;
    array array_type;
    cnt int;
    
    before each row is
        hinhthuc_ CABENH.hinhthuc%type := :new.hinhthuc;
        tenkhoa_ Benh.tenkhoa%type;
    begin
        select tenkhoa into tenkhoa_ from Benh where mabenh = :new.mabenh;
        if tenkhoa_ is not null and tenkhoa_ = 'Truyen Nhiem' and hinhthuc_ not in ('Tai gia','Cach ly') then
            raise_application_error(-20014,'Benh thuoc khoa truyen nhiem thi hinh thuc la "Tai gia" hoac "Cach ly"');
        elsif :new.maphong is not null then
            array(array.count+1).mabn := :new.mabn;
            array(array.count).hinhthuc := hinhthuc_;
            array(array.count).tenkhoa := tenkhoa_;
            select loai into array(array.count).loaiphong from PHONGBENH pb where pb.maphong = :new.maphong; 
        end if;
    end before each row;
        
    after statement is 
    begin
        for i in 1..array.count loop
            select count(*) into cnt from CABENH where mabn = array(i).mabn and hinhthuc != 'Tai gia' and tinhtrang != 'Da ket thuc';
            if cnt = 0 and array(i).hinhthuc = 'Tai gia' then 
                raise_application_error(-20025,'Benh nhan tai gia thi ma phong phai de trong');
            elsif array(i).tenkhoa = 'Truyen Nhiem' and array(i).loaiphong != 'Cach ly' then
                raise_application_error(-20023,'Benh nhan co benh truyen nhiem thi phong benh phai thuoc loai Cach ly');
            end if;
        end loop;
    end after statement;
end;
/
--8
create or replace trigger trg_cabenh_khoa
before insert or update of mabenh,mabs
on CABENH
for each row
declare 
    maBS_ BACSI.MaBS%type := :new.mabs;
    maBenh_ CABENH.mabenh%type := :new.mabenh;
    khoaBS BACSI.tenkhoa%type;
    khoaBenh BENH.tenkhoa%type;
begin
    select tenkhoa into khoaBS from BACSI bs where bs.maBS = maBS_;
    select tenkhoa into khoaBenh from BENH b where b.mabenh = maBenh_;
    if khoaBS != khoaBenh then
        raise_application_error(-20008,'Bac si va benh phai thuoc cung mot khoa');
    end if;
end;
/
--9
create or replace trigger trg_cabenh_ngaybatdau_ngayketthuc
before insert or update 
on CABENH
for each row
declare
    ngaybatdauCB CABENH.ngaybatdau%type := :new.ngaybatdau;
    ngayketthucCB CABENH.ngayketthuc%type := :new.ngayketthuc;
    ngayDP_min DIEUPHOITHIETBI.ngaydieuphoi%type;
    ngayketthucDP_max DIEUPHOITHIETBI.ngayketthuc%type;
    maBN_ CABENH.mabn%type := :new.mabn;
    maBS_ CABENH.mabs%type := :new.mabs;
    ngaySinhBN BENHNHAN.NgaySinh%type;
    ngaySinhBS BACSI.NgaySinh%type;
begin
    if ngaybatdauCB is not null and ngayketthucCB is not null and ngaybatdauCB >= ngayketthucCB then
        raise_application_error(-20007,'Thoi diem ket thuc ca benh phai sau thoi diem bat dau');
    elsif ngaybatdauCB is not null and ngaybatdauCB > current_timestamp then
        raise_application_error(-20005,'Thoi diem bat dau ca benh phai la thoi diem hien tai hoac qua khu');
    end if;
    select NgaySinh into ngaySinhBN from BENHNHAN bn where bn.MaBN = maBN_;
    select NgaySinh into ngaySinhBS from BACSI bs where bs.MaBS = maBS_;
    select min(ngaydieuphoi) into ngayDP_min from DIEUPHOITHIETBI dp where dp.maca = :new.maca;
    select max(ngayketthuc) into ngayketthucDP_max from DIEUPHOITHIETBI dp where dp.maca = :new.maca;
    if ngaybatdauCB is not null and ngayketthucCB is not null and trunc(ngaybatdauCB) < ngaySinhBN or trunc(ngaybatdauCB) <= ngaySinhBS then
        raise_application_error(-20013,'Ngay bat dau phai sau ngay sinh cua bac si va sau hoac cung ngay sinh cua benh nhan');
    elsif ngaybatdauCB is not null and ngayDP_min is not null and ngayDP_min < ngaybatdauCB then 
        raise_application_error(-20034,'Thoi diem bat dau ca benh phai som hon hoac cung voi thoi diem dieu phoi thiet bi cho ca benh');
    elsif ngayketthucCB is not null and ngayketthucDP_max is not null and ngayketthucDP_max > ngayketthucCB then
        raise_application_error(-20035,'Thoi diem ket thuc ca benh phai sau hoac cung voi thoi diem ket thuc dieu phoi thiet bi cho ca benh');
    end if;
end;
/
--10
create or replace trigger trg_cabenh_khoaNhi
before insert or update on CABENH
for each row 
declare 
    ngaysinh_ BENHNHAN.NgaySinh%type;
    tenkhoa_ BENH.tenkhoa%type;
    mabn_ CABENH.maBN%type := :new.mabn;
    mabenh_ CABENH.mabenh%type := :new.mabenh;
    ngaybatdau_ CABENH.ngaybatdau%type := :new.ngaybatdau;
    loi exception;
begin
    select NgaySinh into ngaysinh_ from BENHNHAN where mabn = mabn_;
    select tenkhoa into tenkhoa_ from benh where mabenh = mabenh_;
    
    if ngaybatdau_ is not null and 
        extract(year from ngaybatdau_) - extract(year from ngaysinh_) > 15 
        and tenkhoa_ = 'Nhi' and :new.tinhtrang != 'Da ket thuc' then
        raise loi;
    end if;
exception
    when loi then
        raise_application_error(-20017,'Benh nhan khoa Nhi phai nho hon hoac bang 15 tuoi');
end;
/
--11
create or replace trigger trg_cabenh_taigia
for delete on CABENH
compound trigger
    cnt int;
    type r_mabn_type is record ( mabn CABENH.mabn%type);
    type t_mabn_type is table of r_mabn_type index by pls_integer;
    array t_mabn_type;
    
    after each row is
    begin
        if :old.hinhthuc != 'Tai gia'
        and :old.tinhtrang != 'Da ket thuc'  then
            array(array.count+1).mabn := :old.mabn;
        end if;
    end after each row;
    
    after statement is
    begin
        for i in 1..array.count 
        loop
            select count(*) into cnt from CABENH cb where cb.mabn = array(i).mabn and cb.hinhthuc != 'Tai gia' and cb.tinhtrang != 'Da ket thuc';
            if cnt = 0 then
                update CABENH set maphong = null where mabn = array(i).mabn and tinhtrang != 'Da ket thuc';
            end if;
        end loop;
    end after statement;
end;
/
--12
create or replace trigger trg_cabenh_tuongduong
before insert on CABENH
for each row 
declare
    cnt int;
begin
    select count(*) into cnt from CABENH cb where cb.mabn = :new.mabn and cb.mabs = :new.mabs and cb.mabenh = :new.mabenh and tinhtrang != 'Da ket thuc';
    if cnt = 1 then
        raise_application_error(-20004,'Ton tai ca benh tuong tu chua ket thuc');
    end if;
end;
/
--TRIGGER PHONGBENH
--13
create or replace trigger trg_phongbenh_giatri
before insert or update 
on PHONGBENH
for each row 
declare
    toa_ phongbenh.toa%type := :new.toa;
    lau_ phongbenh.lau%type := :new.lau;
    succhua_ phongbenh.succhua%type := :new.succhua;
    cnt int;
begin
    if toa_ <= 0 then raise_application_error(-20019,'Toa khong hop le');
    elsif lau_ <= 0 then raise_application_error(-20020,'Lau khong hop le');
    elsif succhua_ <= 0 then raise_application_error(-20021,'Suc chua toi thieu la 1');
    end if;
    if inserting then :new.controng := succhua_; end if;
    select count(*) into cnt from CABENH cb, BENH be 
    where cb.mabenh = be.mabenh 
    and maphong = :new.maphong 
    and tenkhoa = 'Truyen Nhiem'
    and tinhtrang != 'Da ket thuc';
    if cnt > 0 and :new.loai != 'Cach ly' then
        raise_application_error(-20024,'Phong co ca benh truyen nhiem nen loai phong phai la cach ly');
    end if;
end;
/
--TRIGGER THIETBIYTE
--14
create or replace trigger trg_thietbi_giatri
before insert or update on THIETBIYTE
for each row
begin
    if :new.sltong < 0 or :new.sltong != round(:new.sltong) then
        raise_application_error(-20027,'So luong khong hop le');
    end if;
    if inserting then 
        :new.slconlai := :new.sltong;
    end if;
end;
/
--TRIGGER DIEUPHOITHIETBI
--15
create or replace trigger trg_dieuphoi_giatri
before insert or update on DIEUPHOITHIETBI
for each row
declare
    maca_ DIEUPHOITHIETBI.maca%type := :new.maca;
    tinhtrangCB CABENH.tinhtrang%type;
    ngaybatdauCB CABENH.ngaybatdau%type;
    ngayketthucCB CABENH.ngayketthuc%type;
begin
    select tinhtrang into tinhtrangCB from CABENH where maca = maca_;
    select ngaybatdau into ngaybatdauCB from CABENH where maca = maca_;
    select ngayketthuc into ngayketthucCB from CABENH where maca = maca_;
    if tinhtrangCB = 'Da ket thuc' then
        raise_application_error(-20031,'Khong the dieu phoi hay sua dieu phoi cho ca benh da ket thuc');
    elsif :new.soluong <= 0 or :new.soluong != round(:new.soluong) then 
        raise_application_error(-20029,'So luong dieu phoi khong hop le');
    elsif :new.ngaydieuphoi is not null and :new.ngayketthuc is not null and :new.ngaydieuphoi >= :new.ngayketthuc then
        raise_application_error(-20030,'Thoi diem ket thuc dieu phoi phai sau thoi diem dieu phoi');
    elsif :new.ngaydieuphoi is not null and ngaybatdauCB is not null and :new.ngaydieuphoi < ngaybatdauCB then
        raise_application_error(-20032,'Thoi diem dieu phoi phai sau hoac cung voi thoi diem bat dau ca benh');
    elsif :new.ngayketthuc is not null and ngayketthucCB is not null and :new.ngayketthuc > ngayketthucCB then
        raise_application_error(-20033,'Thoi diem ket thuc dieu phoi phai som hon hoac cung voi thoi diem ket thuc ca benh');
    end if;
end; 
/
--TRIGGER CABENH
--16
create or replace trigger trg_cabenh_taohoadon
after insert on CABENH
for each row
declare
    stt number;
    mahdgannhat HOADONVIENPHI.MAHD%TYPE;
    phandem HOADONVIENPHI.MAHD%TYPE := '';
    dem number;
begin
    SELECT COUNT(MAHD) INTO dem FROM HOADONVIENPHI ORDER BY MAHD DESC;
    IF (dem = 0) THEN
        insert into hoadonvienphi values ('HD001', :new.maca, current_timestamp, 0, 0, 0, 0, '');
    ELSE
        SELECT A.MAHD INTO mahdgannhat FROM 
        ( SELECT B.* FROM (SELECT * FROM HOADONVIENPHI ORDER BY MAHD DESC) B WHERE ROWNUM = 1) A;
        stt := (regexp_replace(mahdgannhat, '[^0-9]', '') + 1);
        IF (stt < 10) THEN
            phandem := '00';
        ELSIF (stt < 100) THEN
            phandem := '0';
        END IF;
        insert into hoadonvienphi values ('HD' || phandem || to_char(stt), :new.maca, current_timestamp, 0, 0, 0, 0, '');
    END IF;
end;
