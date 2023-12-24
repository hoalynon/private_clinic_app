delete from lichhenkham;
delete from hoadonvienphi;
delete from kethuoc;
delete from thuoc;
delete from dieuphoithietbi;
delete from kethuoc;
delete from thuoc;
delete from hoadonvienphi;
delete from lichhenkham;
delete from cabenh;
delete from thietbiyte;
delete from phongbenh;
delete from benh;
delete from benhnhan;
delete from bacsi;
delete from taikhoan;

commit;

drop table dieuphoithietbi;
drop table kethuoc;
drop table thuoc;
drop table hoadonvienphi;
drop table lichhenkham;
drop table cabenh;
drop table thietbiyte;
drop table phongbenh;
drop table benh;
drop table benhnhan;
drop table bacsi;
drop table taikhoan;

commit;

alter SESSION set NLS_DATE_FORMAT = 'DD/MM/YYYY';

CREATE TABLE BACSI
(
	MaBS VARCHAR(10) NOT NULL,
	HoTen VARCHAR2(100) NOT NULL,
	GioiTinh VARCHAR(10),
	NgaySinh DATE NOT NULL,
	QueQuan VARCHAR(100),
	NoiOHienTai VARCHAR(100),
	TenKhoa VARCHAR(50) NOT NULL,
	NamPhucVu NUMBER,
	CONSTRAINT PK_BACSI PRIMARY KEY (MaBS),
	CONSTRAINT CK_BACSI_TENKHOA CHECK(TENKHOA IN ('Hoi Suc Cap Cuu', 'Noi Tong Hop', 'Noi Tim Mach', 'Noi Tieu Hoa', 'Noi Co-Xuong-Khop', 'Noi Than-Tiet Nieu','Noi Tiet','Di Ung','Truyen Nhiem','Lao','Da Lieu','Than Kinh', 'Tam Than','Y Hoc Co Truyen','Nhi','Ngoai Tong Hop','Chan Thuong Chinh Hinh','Bong','Phu San','Tai-Mui-Hong','Rang-Ham-Mat','Mat','Vat Ly Tri Lieu','Y Hoc Hat Nhat','Truyen Mau','Loc Mau','Hoa Sinh','Vi Sinh','Chuan Doan Hinh Anh','Noi Soi','Duoc','Dinh Duong','Cap Cuu','Ung Buou'))
);
commit;

CREATE TABLE BENHNHAN
(
	MaBN VARCHAR(10) NOT NULL,
	HoTen VARCHAR2(100) NOT NULL,
	GioiTinh VARCHAR(10),
	NgaySinh DATE NOT NULL,
	QueQuan VARCHAR(100),
	NoiOHienTai VARCHAR(100),
	KhaNangDatLich NUMBER,
	CONSTRAINT PK_BENHNHAN PRIMARY KEY (MaBN)
);

commit;

create table benh
(
    mabenh varchar(50),
    tenbenh varchar2(100) NOT NULL,
    tenkhoa varchar(50) NOT NULL,
    gia integer NOT NULL,
    constraint pk_benh primary key (mabenh),
    constraint ck_benh_gia check (gia >= 0),
    CONSTRAINT CK_BENH_TENKHOA CHECK(TENKHOA IN ('Hoi Suc Cap Cuu', 'Noi Tong Hop', 'Noi Tim Mach', 'Noi Tieu Hoa', 'Noi Co-Xuong-Khop', 'Noi Than-Tiet Nieu','Noi Tiet','Di Ung','Truyen Nhiem','Lao','Da Lieu','Than Kinh', 'Tam Than','Y Hoc Co Truyen','Nhi','Ngoai Tong Hop','Chan Thuong Chinh Hinh','Bong','Phu San','Tai-Mui-Hong','Rang-Ham-Mat','Mat','Vat Ly Tri Lieu','Y Hoc Hat Nhat','Truyen Mau','Loc Mau','Hoa Sinh','Vi Sinh','Chuan Doan Hinh Anh','Noi Soi','Duoc','Dinh Duong','Cap Cuu','Ung Buou'))
);

commit;

create table phongbenh
(
    maphong varchar(10),
    loai varchar(20),
    toa number,
    lau number,
    succhua number NOT NULL,
    controng number,
    gia1ngay integer,
    constraint pk_phongbenh primary key (maphong),
    constraint ck_phongbenh_gia1ngay check (gia1ngay >= 0)
);
commit;

create table cabenh
(
    maca varchar(20) not null unique,
    mabn varchar(10),
    mabs varchar(10),
    mabenh varchar(50),
    mucdo varchar2(20) CHECK (mucdo IN ('Khong cap cuu','Hoi suc','Nang','Cham soc dac biet','Cap cuu')),
    hinhthuc varchar(20) CHECK (hinhthuc IN ('Tai gia','Nhap vien','Cach ly')),
    ngaybatdau timestamp,
    ngayketthuc timestamp,
    tinhtrang varchar(20) CHECK (tinhtrang IN ('Trieu chung','Chuan doan','Dieu tri','Giam sat','Cham soc','Da ket thuc')),
    maphong varchar(10),
    ngaychuyengannhat timestamp,
    constraint pk_cabenh primary key (mabn,mabs,mabenh,ngaybatdau),
    constraint fk_cabenh_mabn foreign key (mabn) references benhnhan(mabn),
    constraint fk_cabenh_mabs foreign key (mabs) references bacsi(mabs),
    constraint fk_cabenh_mabenh foreign key (mabenh) references benh(mabenh),
    constraint fk_cabenh_maphong foreign key (maphong) references phongbenh (maphong)
);

commit;

create table thietbiyte
(
    mathietbi varchar(10),
    tenthietbi varchar2(50) NOT NULL,
    loaisd varchar2(20) CHECK (loaisd IN ('1 lan','Tai su dung')),
    congdung varchar2(200),
    sltong number NOT NULL,
    slconlai number,
    gia integer,
    constraint pk_thietbiyte primary key (mathietbi),
    constraint ck_thietbiyte_gia check (gia >= 0)
);

commit;

create table dieuphoithietbi
(
    maca varchar(20),
    mathietbi varchar(10),
    soluong number NOT NULL,
    ngaydieuphoi timestamp,
    ngayketthuc timestamp,
    constraint pk_dieuphoithietbi primary key (maca,mathietbi,ngaydieuphoi),
    constraint fk_dieuphoithietbi_maca foreign key (maca) references cabenh(maca),
    constraint fk_dieuphoithietbi_mathietbi foreign key (mathietbi) references thietbiyte(mathietbi)
);

commit;

create table taikhoan
(
    tendangnhap varchar(10),
    matkhau varchar(100),
    constraint pk_taikhoan primary key (tendangnhap)
);

commit;

create table thuoc
(
    mathuoc varchar(10),
    tenthuoc varchar2(50) NOT NULL,
    congdung varchar2(200) NOT NULL,
    slconlai number NOT NULL,
    gia integer,
    constraint pk_thuoc primary key (mathuoc),
    constraint ck_thuoc_gia check (gia >= 0),
    constraint ck_thuoc_slconlai check (slconlai >= 0)
);

commit;

create table kethuoc
(
    maca varchar(10),
    mathuoc varchar(10),
    ngayke timestamp,
    sl number,
    constraint pk_kethuoc primary key (maca, mathuoc, ngayke),
    constraint fk_kethuoc_maca foreign key (maca) references cabenh(maca) on delete cascade,
    constraint fk_kethuoc_mathuoc foreign key (mathuoc) references thuoc(mathuoc),
    constraint ck_kethuoc_sl check (sl >= 0)
);

commit;

create table hoadonvienphi
(
    mahd varchar(10),
    maca varchar(10) not null,
    ngaylap timestamp not null,
    tongtien integer not null,
    tienthuoc integer not null,
    tienkham integer not null,
    trangthai number not null,
    ghichu varchar2(500),
    constraint fk_hoadonvienphi_maca foreign key (maca) references cabenh(maca) on delete cascade,
    constraint pk_hoadonvienphi primary key (mahd)
);

commit;

create table lichhenkham
(
    malich varchar(10),
    mabn varchar(10) not null,
    mabs varchar(10),
    ngaydukien timestamp not null,
    nhucaukham varchar2(500) not null,
    qlxacnhan number not null,
    bnxacnhan number not null,
    constraint pk_lichhenkham primary key (malich),
    constraint fk_lichhenkham_mabn foreign key (mabn) references benhnhan(mabn) on delete cascade
);

commit;

INSERT INTO bacsi VALUES ('BS001', 'Le Tam Khoa', 'Nam', '08/04/1987', '53 Nguyen Hue, My Tho, Tien Giang', '123 Le Loi, Thanh pho Thu Dau Mot, Binh Duong', 'Cap Cuu', 6);
INSERT INTO bacsi VALUES ('BS002', 'Tran Minh An', 'Nam', '25/09/1976', '35 Tran Phu, Le Loi, Quang Ngai', '456 Le Duan, Cam Le, Da Nang', 'Noi Soi', 17);
INSERT INTO bacsi VALUES ('BS003', 'Truong Anh Khanh', 'Nu', '17/11/1979', '36 Tran Hung Dao, Cam Le, Da Nang', '789 Tran Phu, Dong Son, Thanh Hoa', 'Nhi', 14);
INSERT INTO bacsi VALUES ('BS004', 'Phan Ngoc Quynh', 'Nu', '12/06/1971', '45 Le Loi, Thanh Hoa', '321 Nguyen Hue, Ben Thanh, Thanh pho Ho Chi Minh', 'Nhi', 22);
INSERT INTO bacsi VALUES ('BS005', 'Nguyen Thanh Binh', 'Nu', '21/08/1973', '56 Nguyen Hue, Quan 1, Thanh pho Ho Chi Minh', '654 Le Loi, Hai Chau, Da Nang', 'Noi Soi', 20);
INSERT INTO bacsi VALUES ('BS006', 'Phan Minh Lien', 'Nu', '30/12/1979', '78 Le Duan, Hai Chau, Da Nang', '987 Le Thanh Ton, Le Hong Phong, Thanh Hoa', 'Noi Soi', 14);
INSERT INTO bacsi VALUES ('BS007', 'Nguyen Minh Duong', 'Nu', '15/03/1975', '23 Nguyen Trai, Thanh Hoa', '147 Nguyen Van Linh, Hai Chau, Da Nang', 'Noi Soi', 18);
INSERT INTO bacsi VALUES ('BS008', 'Truong Tri Khanh', 'Nam', '09/07/1971', '56 Tran Phu, Ha Long, Quang Ninh', '135 Le Thanh Tong, Hong Gai, Quang Ninh', 'Truyen Nhiem', 22);
INSERT INTO bacsi VALUES ('BS009', 'Phan Thanh An', 'Nam', '14/10/1989', '23 Nguyen Chi Thanh, Di An, Binh Duong', '246 Le Loi, Tan Lap, Binh Duong', 'Nhi', 4);
INSERT INTO bacsi VALUES ('BS010', 'Phan Minh Ha', 'Nam', '22/01/1985', '12 Tran Hung Dao, Long Xuyen, An Giang', '963 Tran Hung Dao, My An, An Giang', 'Ung Buou', 8);
INSERT INTO bacsi VALUES ('BS011', 'Le Tam Khoa', 'Nam', '06/11/1980', '23 Nguyen Trai, Thai Nguyen', '753 Nguyen Van Cu, Quyet Thang, Thai Nguyen', 'Ung Buou', 13);
INSERT INTO bacsi VALUES ('BS012', 'Vo Thanh Ha', 'Nam', '29/03/1975', '45 Tran Hung Dao, Ninh Kieu, Can Tho', '852 Le Hong Phong, Ninh Kieu, Thanh pho Hai Phong', 'Cap Cuu', 18);
INSERT INTO bacsi VALUES ('BS013', 'Hoang Anh Tu', 'Nam', '02/02/1979', '36 Tran Hung Dao, Cam Le, Da Nang', '369 Le Duan, Cam Le, Da Nang', 'Ung Buou', 14);
INSERT INTO bacsi VALUES ('BS014', 'Nguyen Tam Hoa', 'Nam', '18/07/1971', '45 Tran Hung Dao, Hai Chau, Da Nang', '951 Nguyen Van Linh, Hai Chau, Da Nang', 'Ung Buou', 22);
INSERT INTO bacsi VALUES ('BS015', 'Nguyen Tri Hao', 'Nu', '09/05/1986', '56 Nguyen Hue, Quan 1, Thanh pho Ho Chi Minh', '753 Le Duan, Hai Chau, Da Nang', 'Cap Cuu', 7);
INSERT INTO bacsi VALUES ('BS016', 'Tran Kim Khoa', 'Nam', '24/09/1980', '23 Nguyen Chi Thanh, Di An, Binh Duong', '369 Le Loi, Thu Dau Mot, Binh Duong', 'Nhi', 13);
INSERT INTO bacsi VALUES ('BS017', 'Pham Tri Hao', 'Nam', '16/12/1972', '45 Tran Hung Dao, Hai Chau, Da Nang', '753 Nguyen Chi Thanh, Hai Chau, Da Nang', 'Cap Cuu', 21);
INSERT INTO bacsi VALUES ('BS018', 'Pham Kim Duong', 'Nam', '10/02/1977', '67 Tran Phu, Nha Trang, Khanh Hoa', '963 Le Loi, Nha Trang, Khanh Hoa', 'Noi Soi', 16);
INSERT INTO bacsi VALUES ('BS019', 'Le Tam Hao', 'Nam', '05/09/1971', '89 Nguyen Chi Thanh, Buon Ma Thuot, Dak Lak', '753 Nguyen Van Linh, Buon Ma Thuot, Dak Lak', 'Nhi', 22);
INSERT INTO bacsi VALUES ('BS020', 'Le Tri Hoa', 'Nam', '27/11/1972', '36 Tran Hung Dao, Cam Le, Da Nang', '963 Le Duan, Cam Le, Da Nang', 'Nhi', 20);

COMMIT;

INSERT INTO benhnhan VALUES ('BN001', 'Tran Anh Lien', 'Nu', '21/03/1992', '36 Duong Le Loi, Phuong Ben Nghe, Quan 1, Thanh pho Ho Chi Minh', '42 Duong Nguyen Hue, Phuong Ben Thanh, Quan 1, Thanh pho Ho Chi Minh', 1);
INSERT INTO benhnhan VALUES ('BN002', 'Le Ngoc Quynh', 'Nu', '15/07/1985', '18 Duong Tran Phu, Phuong Loc Tho, Thanh pho Nha Trang, Tinh Khanh Hoa', '57 Duong Le Hong Phong, Phuong 1, Thanh pho Vung Tau, Tinh Ba Ria - Vung Tau', 1);
INSERT INTO benhnhan VALUES ('BN003', 'Vo Tri Quynh', 'Nam', '03/09/1988', '23 Duong Tran Hung Dao, Phuong Vinh Thanh, Thanh pho Rach Gia, Tinh Kien Giang', '12 Duong Hung Vuong, Phuong Le Loi, Thanh pho Hue, Tinh Thua Thien Hue', 1);
INSERT INTO benhnhan VALUES ('BN004', 'Nguyen Anh Quynh', 'Nu', '15/07/1999', '35 Duong Nguyen Thi Minh Khai, Phuong Ben Nghe, Quan 1, Thanh pho Ho Chi Minh', '72 Duong Tran Phu, Phuong Phong Nam, Thanh pho Phan Thiet, Tinh Binh Thuan', 1);
INSERT INTO benhnhan VALUES ('BN005', 'Hoang Minh Ha', 'Nu', '28/05/1994', '30 Duong Ham Nghi, Phuong Nguyen Thai Binh, Quan 1, Thanh pho Ho Chi Minh', '39 Duong Le Duong, Phuong Ben Nghe, Quan 1, Thanh pho Ho Chi Minh', 1);
INSERT INTO benhnhan VALUES ('BN006', 'Pham Minh An', 'Nu', '17/08/1987', '51 Duong Nguyen Hue, Phuong Ben Thanh, Quan 1, Thanh pho Ho Chi Minh', '51 Duong Nguyen Hue, Phuong Ben Thanh, Quan 1, Thanh pho Ho Chi Minh', 1);
INSERT INTO benhnhan VALUES ('BN007', 'Le Thi Phuong', 'Nu', '05/11/1991', '29 Duong Nguyen Thi Minh Khai, Phuong Da, Quan 1, Thanh pho Ho Chi Minh', '44 Duong Tran Quang Dieu, Phuong Phuoc Ninh, Thanh pho Da Nang', 1);
INSERT INTO benhnhan VALUES ('BN008', 'Vo Anh Khanh', 'Nam', '09/02/2015', '75 Duong Tran Hung Dao, Phuong Phuoc My, Thanh pho Quy Nhon, Tinh Binh Dinh', '20 Duong Tran Phu, Phuong 7, Thanh pho Ca Mau, Tinh Ca Mau', 1);
INSERT INTO benhnhan VALUES ('BN009', 'Tran Tam Tu', 'Nam', '12/06/2010', '63 Duong Nguyen Thi Minh Khai, Phuong Ben Nghe, Quan 1, Thanh pho Ho Chi Minh', '37 Duong Le Duong, Phuong Ben Nghe, Quan 1, Thanh pho Ho Chi Minh', 1);
INSERT INTO benhnhan VALUES ('BN010', 'Truong Kim An', 'Nam', '08/04/1993', '55 Duong Nguyen Hue, Phuong Ben Thanh, Quan 1, Thanh pho Ho Chi Minh', '82 Duong Tran Phu, Phuong Xuan An, Thanh pho Phan Thiet, Tinh Binh Thuan', 1);
INSERT INTO benhnhan VALUES ('BN011', 'Nguyen Minh Phuong', 'Nam', '27/10/1997', '26 Duong Le Hong Phong, Phuong 4, Thanh pho Vung Tau, Tinh Ba Ria - Vung Tau', '48 Duong Tran Hung Dao, Phuong An Binh, Thanh pho Ha Long, Tinh Quang Ninh', 1);
INSERT INTO benhnhan VALUES ('BN012', 'Tran Thi Hao', 'Nam', '14/09/1986', '14 Duong Phan Chu Trinh, Phuong Da Kao, Quan 1, Thanh pho Ho Chi Minh', '33 Duong Hung Vuong, Phuong Hoa Cuong Bac, Quan Hai Chau, Thanh pho Da Nang', 1);
INSERT INTO benhnhan VALUES ('BN013', 'Hoang Tam Hao', 'Nu', '02/07/1995', '67 Duong Nguyen Thi Minh Khai, Phuong Ben Thanh, Quan 1, Thanh pho Ho Chi Minh', '41 Duong Tran Quang Dieu, Phuong Hoa Tho Tay, Quan Cam Le, Thanh pho Da Nang', 1);
INSERT INTO benhnhan VALUES ('BN014', 'Le Thu Phuong', 'Nam', '19/01/2016', '72 Duong Tran Hung Dao, Phuong Truong Xuan, Thanh pho Tam Ky', '19 Duong Tran Phu, Phuong Hau Giang, Thanh pho Can Tho, Tinh Can Tho', 1);
INSERT INTO benhnhan VALUES ('BN015', 'Truong Minh Hoa', 'Nu', '09/11/1988', '58 Duong Le Hong Phong, Phuong 5, Thanh pho Vung Tau, Tinh Ba Ria - Vung Tau', '24 Duong Tran Hung Dao, Phuong Vinh Hai, Thanh pho Nha Trang, Tinh Khanh Hoa', 1);
INSERT INTO benhnhan VALUES ('BN016', 'Truong Quoc Khanh', 'Nu', '16/06/1992', '13 Duong Hung Vuong, Phuong Vinh Hai, Thanh pho Nha Trang, Tinh Khanh Hoa', '31 Duong Ham Nghi, Phuong Cau Ong Lanh, Quan 1, Thanh pho Ho Chi Minh', 1);
INSERT INTO benhnhan VALUES ('BN017', 'Pham Anh Ha', 'Nam', '25/03/1987', '46 Duong Le Duong, Phuong Ben Nghe, Quan 1, Thanh pho Ho Chi Minh', '53 Duong Nguyen Hue, Phuong Ben Thanh, Quan 1, Thanh pho Ho Chi Minh', 1);
INSERT INTO benhnhan VALUES ('BN018', 'Truong Tam Duong', 'Nu', '13/02/2015', '80 Duong Tran Phu, Phuong Van Thanh, Thanh pho Vinh, Tinh Nghe An', '27 Duong Nguyen Thi Minh Khai, Phuong Ben Thanh, Quan 1, Thanh pho Ho Chi Minh', 1);
INSERT INTO benhnhan VALUES ('BN019', 'Vo Ngoc Tu', 'Nam', '06/05/1991', '42 Duong Tran Quang Dieu, Phuong Hoa Tho Dong, Quan Cam Le, Thanh pho Da Nang', '67 Duong Tran Hung Dao, Phuong 1, Thanh pho Vung Tau, Tinh Ba Ria - Vung Tau', 1);
INSERT INTO benhnhan VALUES ('BN020', 'Vo Anh Tu', 'Nam', '22/07/1985', '16 Duong Phan Chu Trinh, Phuong Ben Nghe, Quan 1, Thanh pho Ho Chi Minh', '35 Duong Hung Vuong, Phuong Thach Thang, Thanh pho Hue, Tinh Thua Thien Hue', 1);
INSERT INTO benhnhan VALUES ('BN021', 'Truong Quoc Hoa', 'Nu', '11/09/1990', '60 Duong Nguyen Thi Minh Khai, Phuong Ben Thanh, Quan 1, Thanh pho Ho Chi Minh', '22 Duong Tran Quang Dieu, Phuong Thac Gian, Quan Cam Le, Thanh pho Da Nang', 1);
INSERT INTO benhnhan VALUES ('BN022', 'Nguyen Thanh Khanh', 'Nam', '30/12/2017', '39 Duong Tran Hung Dao, Phuong Phuoc Trung, Thanh pho Quy Nhon, Tinh Binh Dinh', '74 Duong Le Hong Phong, Phuong 7, Thanh pho Vung Tau, Tinh Ba Ria - Vung Tau', 1);
INSERT INTO benhnhan VALUES ('BN023', 'Hoang Tri Khoa', 'Nu', '18/06/1988', '18 Duong Le Duong, Phuong Ben Nghe, Quan 1, Thanh pho Ho Chi Minh', '50 Duong Nguyen Hue, Phuong Ben Thanh, Quan 1, Thanh pho Ho Chi Minh', 1);
INSERT INTO benhnhan VALUES ('BN024', 'Le Thanh Ha', 'Nam', '07/04/1992', '50 Duong Nguyen Hue, Phuong Ben Thanh, Quan 1, Thanh pho Ho Chi Minh', '25 Duong Le Hong Phong, Phuong 2, Thanh pho Vung Tau, Tinh Ba Ria - Vung Tau', 1);
INSERT INTO benhnhan VALUES ('BN025', 'Vo Ngoc Quynh', 'Nam', '24/10/1996', '44 Duong Tran Hung Dao, Phuong Vinh Hoa, Thanh pho Rach Gia, Tinh Kien Giang', '69 Duong Nguyen Thi Minh Khai, Phuong Da Kao, Quan 1, Thanh pho Ho Chi Minh', 1);
INSERT INTO benhnhan VALUES ('BN026', 'Tran Thu Hoa', 'Nu', '01/03/2013', '36 Duong Tran Quang Dieu, Phuong An Hai Bac, Quan Son Tra, Thanh pho Da Nang', '61 Duong Le Duong, Phuong Ben Nghe, Quan 1, Thanh pho Ho Chi Minh', 1);
INSERT INTO benhnhan VALUES ('BN027', 'Tran Anh Duong', 'Nu', '20/09/1990', '33 Duong Hung Vuong, Phuong Phuong Sai, Thanh pho Tuy Hoa, Tinh Phu Yen', '58 Duong Tran Quang Dieu, Phuong Thac Gian, Quan Cam Le, Thanh pho Da Nang', 1);
INSERT INTO benhnhan VALUES ('BN028', 'Nguyen Thu Khanh', 'Nam', '09/07/1983', '71 Duong Nguyen Thi Minh Khai, Phuong Ben Thanh, Quan 1, Thanh pho Ho Chi Minh', '46 Duong Tran Hung Dao, Phuong Da Kao, Quan 1, Thanh pho Ho Chi Minh', 1);
INSERT INTO benhnhan VALUES ('BN029', 'Hoang Tri Duong', 'Nu', '26/04/1995', '20 Duong Le Duong, Phuong 7, Thanh pho Vung Tau, Tinh Ba Ria - Vung Tau', '39 Duong Nguyen Hue, Phuong Ben Thanh, Quan 1, Thanh pho Ho Chi Minh', 1);
INSERT INTO benhnhan VALUES ('BN030', 'Truong Ngoc Hieu', 'Nam', '10/11/1991', '64 Duong Tran Phu, Phuong Phong Nam, Thanh pho Phan Thiet, Tinh Binh Thuan', '27 Duong Le Hong Phong, Phuong 5, Thanh pho Vung Tau, Tinh Ba Ria - Vung Tau', 1);
INSERT INTO benhnhan VALUES ('BN031', 'Phan Thanh Hieu', 'Nu', '28/02/1987', '52 Duong Tran Hung Dao, Phuong Van Thanh, Thanh pho Vinh, Tinh Nghe An', '15 Duong Tran Quang Dieu, Phuong Phuoc Trung, Thanh pho Quy Nhon, Tinh Binh Dinh', 1);
INSERT INTO benhnhan VALUES ('BN032', 'Phan Tri Quynh', 'Nu', '14/05/2009', '30 Duong Phan Chu Trinh, Phuong Ben Nghe, Quan 1, Thanh pho Ho Chi Minh', '49 Duong Nguyen Thi Minh Khai, Phuong Thach Thang, Thanh pho Hue, Tinh Thua Thien Hue', 1);
INSERT INTO benhnhan VALUES ('BN033', 'Le Minh Binh', 'Nam', '03/08/1996', '76 Duong Le Duong, Phuong Ben Nghe, Quan 1, Thanh pho Ho Chi Minh', '23 Duong Nguyen Hue, Phuong Ben Thanh, Quan 1, Thanh pho Ho Chi Minh', 1);
INSERT INTO benhnhan VALUES ('BN034', 'Pham Anh Hao', 'Nu', '18/11/1993', '40 Duong Tran Quang Dieu, Phuong An Hai Bac, Quan Son Tra, Thanh pho Da Nang', '65 Duong Tran Hung Dao, Phuong 1, Thanh pho Vung Tau, Tinh Ba Ria - Vung Tau', 1);
INSERT INTO benhnhan VALUES ('BN035', 'Nguyen Ngoc Quynh', 'Nam', '07/07/1989', '18 Duong Le Hong Phong, Phuong Ben Thanh, Quan 1, Thanh pho Ho Chi Minh', '55 Duong Nguyen Thi Minh Khai, Phuong Ben Nghe, Quan 1, Thanh pho Ho Chi Minh', 1);
INSERT INTO benhnhan VALUES ('BN036', 'Nguyen Minh Phuong', 'Nam', '25/09/1984', '82 Duong Tran Phu, Phuong Hau Giang, Thanh pho Can Tho, Tinh Can Tho', '26 Duong Le Hong Phong, Phuong 2, Thanh pho Vung Tau, Tinh Ba Ria - Vung Tau', 1);
INSERT INTO benhnhan VALUES ('BN037', 'Vo Anh Tuan', 'Nu', '13/01/1992', '43 Duong Tran Hung Dao, Phuong Vinh Hai, Thanh pho Nha Trang, Tinh Khanh Hoa', '68 Duong Nguyen Thi Minh Khai, Phuong Da Kao, Quan 1, Thanh pho Ho Chi Minh', 1);
INSERT INTO benhnhan VALUES ('BN038', 'Truong Ngoc Khoa', 'Nam', '30/05/2012', '35 Duong Tran Quang Dieu, Phuong Hoa Tho Dong, Quan Cam Le, Thanh pho Da Nang', '60 Duong Tran Hung Dao, Phuong Phuoc Trung, Thanh pho Quy Nhon, Tinh Binh Dinh', 1);
INSERT INTO benhnhan VALUES ('BN039', 'Tran Anh Hieu', 'Nu', '19/03/1991', '13 Duong Phan Chu Trinh, Phuong Ben Thanh, Quan 1, Thanh pho Ho Chi Minh', '28 Duong Nguyen Hue, Phuong Ben Thanh, Quan 1, Thanh pho Ho Chi Minh', 1);
INSERT INTO benhnhan VALUES ('BN040', 'Nguyen Minh Tuan', 'Nam', '06/08/2013', '45 Duong Tran Quang Dieu, Phuong Thac Gian, Quan Cam Le, Thanh pho Da Nang', '62 Duong Tran Hung Dao, Phuong Van Thanh, Thanh pho Vinh, Tinh Nghe An', 1);
INSERT INTO benhnhan VALUES ('BN041', 'Nguyen Thi Quynh', 'Nu', '12/07/1993', '17 Duong Le Hong Phong, Phuong 5, Thanh pho Vung Tau, Tinh Ba Ria - Vung Tau', '42 Duong Nguyen Thi Minh Khai, Phuong Ben Nghe, Quan 1, Thanh pho Ho Chi Minh', 1);
INSERT INTO benhnhan VALUES ('BN042', 'Le Van Anh', 'Nam', '29/09/1988', '67 Duong Tran Phu, Phuong Phong Nam, Thanh pho Phan Thiet, Tinh Binh Thuan', '24 Duong Le Duong, Phuong Ben Thanh, Quan 1, Thanh pho Ho Chi Minh', 1);
INSERT INTO benhnhan VALUES ('BN043', 'Pham Thi Hien', 'Nu', '08/12/1992', '39 Duong Nguyen Hue, Phuong Ben Thanh, Quan 1, Thanh pho Ho Chi Minh', '54 Duong Tran Quang Dieu, Phuong An Hai Bac, Quan Son Tra, Thanh pho Da Nang', 1);
INSERT INTO benhnhan VALUES ('BN044', 'Tran Minh Duc', 'Nam', '17/02/1986', '69 Duong Tran Hung Dao, Phuong 1, Thanh pho Vung Tau, Tinh Ba Ria - Vung Tau', '16 Duong Le Hong Phong, Phuong Ben Thanh, Quan 1, Thanh pho Ho Chi Minh', 1);
INSERT INTO benhnhan VALUES ('BN045', 'Hoang Van Tuan', 'Nam', '05/06/2008', '51 Duong Nguyen Thi Minh Khai, Phuong Ben Nghe, Quan 1, Thanh pho Ho Chi Minh', '78 Duong Tran Phu, Phuong Hau Giang, Thanh pho Can Tho, Tinh Can Tho', 1);
INSERT INTO benhnhan VALUES ('BN046', 'Nguyen Thi My', 'Nu', '22/09/1995', '25 Duong Le Hong Phong, Phuong 2, Thanh pho Vung Tau, Tinh Ba Ria - Vung Tau', '41 Duong Tran Hung Dao, Phuong Vinh Hai, Thanh pho Nha Trang, Tinh Khanh Hoa', 1);
INSERT INTO benhnhan VALUES ('BN047', 'Le Minh Tuan', 'Nam', '10/03/1991', '66 Duong Nguyen Thi Minh Khai, Phuong Da Kao, Quan 1, Thanh pho Ho Chi Minh', '33 Duong Tran Quang Dieu, Phuong Hoa Tho Dong, Quan Cam Le, Thanh pho Da Nang', 1);
INSERT INTO benhnhan VALUES ('BN048', 'Truong Thi Huyen', 'Nu', '27/06/1987', '58 Duong Tran Hung Dao, Phuong Phuoc Trung, Thanh pho Quy Nhon, Tinh Binh Dinh', '12 Duong Phan Chu Trinh, Phuong Ben Thanh, Quan 1, Thanh pho Ho Chi Minh', 1);
INSERT INTO benhnhan VALUES ('BN049', 'Vo Minh Hieu', 'Nam', '14/10/1993', '29 Duong Nguyen Hue, Phuong Ben Thanh, Quan 1, Thanh pho Ho Chi Minh', '44 Duong Tran Quang Dieu, Phuong Thac Gian, Quan Cam Le, Thanh pho Da Nang', 1);
INSERT INTO benhnhan VALUES ('BN050', 'Nguyen Van Tu', 'Nam', '03/01/1989', '61 Duong Tran Hung Dao, Phuong Van Thanh, Thanh pho Vinh, Tinh Nghe An', '18 Duong Le Hong Phong, Phuong 5, Thanh pho Vung Tau, Tinh Ba Ria - Vung Tau', 1);

COMMIT;

INSERT INTO benh VALUES ('BE001', 'Tai Mui Hong', 'Nhi', 400000);
INSERT INTO benh VALUES ('BE002', 'Viem Thanh Quan', 'Nhi', 550000);
INSERT INTO benh VALUES ('BE003', 'Soi', 'Nhi', 250000);
INSERT INTO benh VALUES ('BE004', 'Dau Mat Do', 'Nhi', 350000);
INSERT INTO benh VALUES ('BE005', 'Thuy Dau', 'Nhi', 300000);
INSERT INTO benh VALUES ('BE006', 'Sot Ret', 'Truyen Nhiem', 500000);
INSERT INTO benh VALUES ('BE007', 'Cum', 'Truyen Nhiem', 350000);
INSERT INTO benh VALUES ('BE008', 'Sot Xuat Huyet', 'Truyen Nhiem', 657000);
INSERT INTO benh VALUES ('BE009', 'Covid 19', 'Truyen Nhiem', 200000);
INSERT INTO benh VALUES ('BE010', 'Dich Hach', 'Truyen Nhiem', 250000);
INSERT INTO benh VALUES ('BE011', 'Dot Quy', 'Cap Cuu', 5800000);
INSERT INTO benh VALUES ('BE012', 'Nhoi Mau Co Tim', 'Cap Cuu', 1600000);
INSERT INTO benh VALUES ('BE013', 'Chan Thuong So Nao', 'Cap Cuu', 3300000);
INSERT INTO benh VALUES ('BE014', 'Bong', 'Cap Cuu', 1000000);
INSERT INTO benh VALUES ('BE015', 'Ngo Doc', 'Cap Cuu', 850000);
INSERT INTO benh VALUES ('BE016', 'Ung Thu Da Day', 'Ung Buou', 4000000);
INSERT INTO benh VALUES ('BE017', 'Ung Thu Da', 'Ung Buou', 500000);
INSERT INTO benh VALUES ('BE018', 'Ung Thu Gan', 'Ung Buou', 4500000);
INSERT INTO benh VALUES ('BE019', 'Ung Thu Phoi', 'Ung Buou', 2050000);
INSERT INTO benh VALUES ('BE020', 'Ung Thu Vu', 'Ung Buou', 1750000);
INSERT INTO benh VALUES ('BE021', 'Viem Tui Mat', 'Noi Soi', 5700000);
INSERT INTO benh VALUES ('BE022', 'Cat Amidan', 'Noi Soi', 12500000);
INSERT INTO benh VALUES ('BE023', 'Cat Ruot Thua', 'Noi Soi', 19000000);
INSERT INTO benh VALUES ('BE024', 'Soi Than', 'Noi Soi', 15000000);
INSERT INTO benh VALUES ('BE025', 'Thoat Vi Dia Dem', 'Noi Soi', 40000000);

COMMIT;

INSERT INTO phongbenh VALUES ('PH101', 'Thuong', 1, 1, 8, 5, 250000);
INSERT INTO phongbenh VALUES ('PH102', 'Thuong', 1, 1, 8, 7, 250000);
INSERT INTO phongbenh VALUES ('PH103', 'Thuong', 1, 1, 6, 5, 350000);
INSERT INTO phongbenh VALUES ('PH104', 'Thuong', 1, 1, 6, 5, 350000);
INSERT INTO phongbenh VALUES ('PH105', 'Thuong', 1, 1, 4, 3, 500000);
INSERT INTO phongbenh VALUES ('PH106', 'Thuong', 1, 2, 8, 7, 250000);
INSERT INTO phongbenh VALUES ('PH107', 'Thuong', 1, 2, 8, 7, 250000);
INSERT INTO phongbenh VALUES ('PH108', 'Thuong', 1, 2, 6, 5, 350000);
INSERT INTO phongbenh VALUES ('PH109', 'Thuong', 1, 2, 6, 5, 350000);
INSERT INTO phongbenh VALUES ('PH110', 'Thuong', 1, 2, 4, 3, 500000);
INSERT INTO phongbenh VALUES ('PH111', 'Thuong', 1, 3, 8, 7, 250000);
INSERT INTO phongbenh VALUES ('PH112', 'Thuong', 1, 3, 8, 7, 250000);
INSERT INTO phongbenh VALUES ('PH113', 'Thuong', 1, 3, 6, 5, 350000);
INSERT INTO phongbenh VALUES ('PH114', 'Thuong', 1, 3, 6, 5, 350000);
INSERT INTO phongbenh VALUES ('PH115', 'Thuong', 1, 3, 4, 3, 500000);
INSERT INTO phongbenh VALUES ('PH116', 'Thuong', 1, 4, 8, 7, 250000);
INSERT INTO phongbenh VALUES ('PH117', 'Thuong', 1, 4, 8, 7, 250000);
INSERT INTO phongbenh VALUES ('PH118', 'Thuong', 1, 4, 6, 5, 350000);
INSERT INTO phongbenh VALUES ('PH119', 'Thuong', 1, 4, 6, 5, 350000);
INSERT INTO phongbenh VALUES ('PH120', 'Thuong', 1, 4, 4, 3, 500000);
INSERT INTO phongbenh VALUES ('PH201', 'VIP', 2, 1, 4, 3, 1000000);
INSERT INTO phongbenh VALUES ('PH202', 'VIP', 2, 1, 2, 1, 3000000);
INSERT INTO phongbenh VALUES ('PH203', 'VIP', 2, 1, 2, 1, 3000000);
INSERT INTO phongbenh VALUES ('PH204', 'VIP', 2, 2, 4, 3, 1000000);
INSERT INTO phongbenh VALUES ('PH205', 'VIP', 2, 2, 2, 1, 3000000);
INSERT INTO phongbenh VALUES ('PH206', 'VIP', 2, 2, 3, 2, 2400000);
INSERT INTO phongbenh VALUES ('PH207', 'VIP', 2, 3, 4, 3, 1000000);
INSERT INTO phongbenh VALUES ('PH208', 'VIP', 2, 3, 3, 2, 2400000);
INSERT INTO phongbenh VALUES ('PH209', 'VIP', 2, 3, 3, 3, 2400000);
INSERT INTO phongbenh VALUES ('PH210', 'VIP', 2, 4, 2, 2, 3000000);
INSERT INTO phongbenh VALUES ('PH301', 'Cach ly', 3, 1, 8, 6, 500000);
INSERT INTO phongbenh VALUES ('PH302', 'Cach ly', 3, 1, 8, 8, 500000);
INSERT INTO phongbenh VALUES ('PH303', 'Cach ly', 3, 1, 4, 4, 1000000);
INSERT INTO phongbenh VALUES ('PH304', 'Cach ly', 3, 1, 4, 3, 1000000);
INSERT INTO phongbenh VALUES ('PH305', 'Cach ly', 3, 1, 1, 0, 4000000);
INSERT INTO phongbenh VALUES ('PH306', 'Cach ly', 3, 1, 2, 2, 2000000);
INSERT INTO phongbenh VALUES ('PH307', 'Cach ly', 3, 1, 3, 3, 1500000);
INSERT INTO phongbenh VALUES ('PH308', 'Cach ly', 3, 1, 1, 1, 4000000);
INSERT INTO phongbenh VALUES ('PH309', 'Cach ly', 3, 2, 8, 8, 500000);
INSERT INTO phongbenh VALUES ('PH310', 'Cach ly', 3, 2, 8, 8, 500000);
INSERT INTO phongbenh VALUES ('PH311', 'Cach ly', 3, 2, 4, 4, 1000000);
INSERT INTO phongbenh VALUES ('PH312', 'Cach ly', 3, 2, 4, 4, 1000000);
INSERT INTO phongbenh VALUES ('PH313', 'Cach ly', 3, 2, 1, 1, 4000000);
INSERT INTO phongbenh VALUES ('PH314', 'Cach ly', 3, 2, 1, 1, 4000000);
INSERT INTO phongbenh VALUES ('PH315', 'Cach ly', 3, 2, 1, 1, 4000000);
INSERT INTO phongbenh VALUES ('PH316', 'Cach ly', 3, 2, 1, 1, 4000000);
INSERT INTO phongbenh VALUES ('PH317', 'Cach ly', 3, 3, 8, 8, 500000);
INSERT INTO phongbenh VALUES ('PH318', 'Cach ly', 3, 3, 8, 8, 500000);
INSERT INTO phongbenh VALUES ('PH319', 'Cach ly', 3, 3, 4, 4, 1000000);
INSERT INTO phongbenh VALUES ('PH320', 'Cach ly', 3, 3, 4, 4, 1000000);
INSERT INTO phongbenh VALUES ('PH321', 'Cach ly', 3, 3, 1, 1, 4000000);
INSERT INTO phongbenh VALUES ('PH322', 'Cach ly', 3, 3, 1, 1, 4000000);
INSERT INTO phongbenh VALUES ('PH323', 'Cach ly', 3, 3, 1, 1, 4000000);
INSERT INTO phongbenh VALUES ('PH324', 'Cach ly', 3, 3, 1, 1, 4000000);
INSERT INTO phongbenh VALUES ('PH325', 'Cach ly', 3, 4, 8, 8, 500000);
INSERT INTO phongbenh VALUES ('PH326', 'Cach ly', 3, 4, 8, 8, 500000);
INSERT INTO phongbenh VALUES ('PH327', 'Cach ly', 3, 4, 4, 4, 1000000);
INSERT INTO phongbenh VALUES ('PH328', 'Cach ly', 3, 4, 4, 4, 1000000);
INSERT INTO phongbenh VALUES ('PH329', 'Cach ly', 3, 4, 1, 1, 4000000);
INSERT INTO phongbenh VALUES ('PH330', 'Cach ly', 3, 4, 1, 1, 4000000);
INSERT INTO phongbenh VALUES ('PH331', 'Cach ly', 3, 4, 1, 1, 4000000);
INSERT INTO phongbenh VALUES ('PH332', 'Cach ly', 3, 4, 1, 1, 4000000);
INSERT INTO phongbenh VALUES ('PH333', 'Cach ly', 3, 5, 8, 8, 500000);
INSERT INTO phongbenh VALUES ('PH334', 'Cach ly', 3, 5, 8, 8, 500000);
INSERT INTO phongbenh VALUES ('PH335', 'Cach ly', 3, 5, 4, 4, 1000000);
INSERT INTO phongbenh VALUES ('PH336', 'Cach ly', 3, 5, 4, 4, 1000000);
INSERT INTO phongbenh VALUES ('PH337', 'Cach ly', 3, 5, 1, 1, 4000000);
INSERT INTO phongbenh VALUES ('PH338', 'Cach ly', 3, 5, 1, 1, 4000000);
INSERT INTO phongbenh VALUES ('PH339', 'Cach ly', 3, 5, 1, 1, 4000000);
INSERT INTO phongbenh VALUES ('PH340', 'Cach ly', 3, 5, 1, 1, 4000000);

COMMIT;

INSERT INTO taikhoan VALUES ('QL001', NULL);
INSERT INTO taikhoan VALUES ('QL002', NULL);
INSERT INTO taikhoan VALUES ('BS001', NULL);
INSERT INTO taikhoan VALUES ('BS002', NULL);
INSERT INTO taikhoan VALUES ('BN001', NULL);
INSERT INTO taikhoan VALUES ('BN002', NULL);
COMMIT;

INSERT INTO cabenh VALUES 
('CA001', 'BN001', 'BS018', 'BE021', 'Cap cuu', 'Nhap vien', TO_TIMESTAMP('10/09/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), 
 TO_TIMESTAMP('01/01/2024 00:00:00','DD/MM/YYYY HH24:MI:SS'), 
 'Chuan doan', 'PH101', 
 TO_TIMESTAMP('10/09/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));

 INSERT INTO cabenh VALUES 
('CA002','BN002', 'BS006', 'BE022', 'Nang', 'Tai gia',  
 TO_TIMESTAMP('10/10/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),
 TO_TIMESTAMP('15/01/2024 00:00:00', 'DD/MM/YYYY HH24:MI:SS'), 
 'Giam sat', NULL, TO_TIMESTAMP('10/10/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'));

INSERT INTO cabenh VALUES
('CA003','BN003', 'BS013', 'BE016', 'Khong cap cuu', 'Nhap vien', 
 TO_TIMESTAMP('12/09/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'), 
 TO_TIMESTAMP('11/12/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),
 'Trieu chung', 'PH101', 
 TO_TIMESTAMP('12/09/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'));

INSERT INTO cabenh VALUES
('CA004','BN004', 'BS014', 'BE017', 'Cham soc dac biet', 'Nhap vien',  
 TO_TIMESTAMP('15/09/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),
 TO_TIMESTAMP('12/01/2024 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),
 'Chuan doan', 'PH101', 
 TO_TIMESTAMP('15/09/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'));

INSERT INTO cabenh VALUES 
('CA005','BN005', 'BS011', 'BE018', 'Hoi suc', 'Nhap vien', 
 TO_TIMESTAMP('20/10/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),
 TO_TIMESTAMP('15/12/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),
 'Giam sat', 'PH102',  
 TO_TIMESTAMP('20/10/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'));

INSERT INTO cabenh VALUES  
('CA006','BN006', 'BS015', 'BE011', 'Nang', 'Tai gia',
 TO_TIMESTAMP('15/10/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'), 
 TO_TIMESTAMP('10/12/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),
 'Dieu tri', NULL, 
 TO_TIMESTAMP('15/10/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'));

INSERT INTO cabenh VALUES  
('CA007','BN007', 'BS010', 'BE019', 'Cap cuu', 'Nhap vien',
 TO_TIMESTAMP('12/09/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),
 TO_TIMESTAMP('12/01/2024 00:00:00', 'DD/MM/YYYY HH24:MI:SS'), 
 'Cham soc', 'PH103', 
 TO_TIMESTAMP('12/09/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'));

INSERT INTO cabenh VALUES
('CA008','BN008', 'BS016', 'BE004', 'Khong cap cuu', 'Nhap vien',  
 TO_TIMESTAMP('17/10/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),
 TO_TIMESTAMP('15/12/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),
 'Chuan doan', 'PH104',
 TO_TIMESTAMP('17/10/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'));

INSERT INTO cabenh VALUES
('CA009','BN009', 'BS020', 'BE003', 'Hoi suc', 'Nhap vien',  
 TO_TIMESTAMP('30/09/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'), 
 TO_TIMESTAMP('12/12/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),
 'Dieu tri', 'PH208',
 TO_TIMESTAMP('30/09/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'));

INSERT INTO cabenh VALUES
('CA010','BN010', 'BS008', 'BE006', 'Cham soc dac biet', 'Tai gia',
 TO_TIMESTAMP('11/10/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),
 TO_TIMESTAMP('15/01/2024 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),
 'Trieu chung', NULL,  
 TO_TIMESTAMP('11/10/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS')); 

INSERT INTO cabenh VALUES
('CA011','BN011', 'BS008', 'BE007', 'Cap cuu', 'Cach ly',  
 TO_TIMESTAMP('15/09/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),
 TO_TIMESTAMP('11/12/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),
 'Giam sat', 'PH301',
 TO_TIMESTAMP('15/09/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'));

INSERT INTO cabenh VALUES
('CA012','BN012', 'BS001', 'BE012', 'Nang', 'Tai gia', 
 TO_TIMESTAMP('15/10/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),
 TO_TIMESTAMP('15/01/2024 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),
 'Chuan doan', NULL,  
 TO_TIMESTAMP('15/10/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'));  

INSERT INTO cabenh VALUES
('CA013','BN013', 'BS017', 'BE013', 'Khong cap cuu', 'Nhap vien',
 TO_TIMESTAMP('18/10/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),
 TO_TIMESTAMP('11/01/2024 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),
 'Dieu tri', 'PH105',
 TO_TIMESTAMP('18/10/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'));

INSERT INTO cabenh VALUES 
('CA014','BN014', 'BS004', 'BE002', 'Hoi suc', 'Nhap vien',
TO_TIMESTAMP('15/10/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),  
TO_TIMESTAMP('15/12/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),
'Cham soc', 'PH106', 
TO_TIMESTAMP('15/10/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'));

INSERT INTO cabenh VALUES
('CA015','BN015', 'BS006', 'BE021', 'Cap cuu', 'Nhap vien',  
TO_TIMESTAMP('18/09/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),
TO_TIMESTAMP('15/12/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),
'Giam sat', 'PH107', 
TO_TIMESTAMP('18/09/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'));

INSERT INTO cabenh VALUES 
('CA016','BN016', 'BS012', 'BE014', 'Cham soc dac biet', 'Nhap vien',   
TO_TIMESTAMP('11/10/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),  
TO_TIMESTAMP('15/01/2024 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),
'Trieu chung', 'PH108',  
TO_TIMESTAMP('11/10/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'));

INSERT INTO cabenh VALUES  
('CA017','BN017', 'BS015', 'BE014', 'Cap cuu', 'Tai gia',
TO_TIMESTAMP('17/09/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),
TO_TIMESTAMP('15/12/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'), 
'Chuan doan', NULL,  
TO_TIMESTAMP('17/09/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'));  

INSERT INTO cabenh VALUES
('CA018','BN018', 'BS009', 'BE003', 'Hoi suc', 'Nhap vien',   
TO_TIMESTAMP('30/09/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),  
TO_TIMESTAMP('11/12/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),
'Dieu tri', 'PH109',
TO_TIMESTAMP('30/09/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'));

INSERT INTO cabenh VALUES  
('CA019','BN019', 'BS007', 'BE022', 'Cham soc dac biet', 'Tai gia',  
TO_TIMESTAMP('15/10/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),
TO_TIMESTAMP('12/12/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),  
'Giam sat', NULL,   
TO_TIMESTAMP('15/10/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'));

INSERT INTO cabenh VALUES 
('CA020','BN020', 'BS002', 'BE023', 'Cap cuu', 'Nhap vien',  
TO_TIMESTAMP('12/09/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),
TO_TIMESTAMP('12/12/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),
'Cham soc', 'PH110',
TO_TIMESTAMP('12/09/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'));   

INSERT INTO cabenh VALUES
('CA021','BN021', 'BS005', 'BE024', 'Nang', 'Tai gia',    
TO_TIMESTAMP('15/10/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),
TO_TIMESTAMP('15/01/2024 00:00:00', 'DD/MM/YYYY HH24:MI:SS'), 
'Chuan doan', NULL,  
TO_TIMESTAMP('15/10/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'));

INSERT INTO cabenh VALUES 
('CA022','BN022', 'BS003', 'BE001', 'Khong cap cuu', 'Nhap vien',   
TO_TIMESTAMP('17/10/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),
TO_TIMESTAMP('15/01/2024 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),
'Dieu tri', 'PH111',
TO_TIMESTAMP('17/10/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'));   

INSERT INTO cabenh VALUES
('CA023','BN023', 'BS008', 'BE008', 'Hoi suc', 'Cach ly',    
TO_TIMESTAMP('15/09/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),  
TO_TIMESTAMP('15/12/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),
'Giam sat', 'PH301',
TO_TIMESTAMP('15/09/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'));

INSERT INTO cabenh VALUES 
('CA024','BN024', 'BS018', 'BE025', 'Cham soc dac biet', 'Nhap vien',
TO_TIMESTAMP('18/09/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),
TO_TIMESTAMP('15/12/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),  
'Trieu chung', 'PH112', 
TO_TIMESTAMP('18/09/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'));  

INSERT INTO cabenh VALUES
('CA025','BN025', 'BS001', 'BE015', 'Cap cuu', 'Tai gia',
TO_TIMESTAMP('15/10/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),
TO_TIMESTAMP('12/01/2024 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),  
'Chuan doan', NULL,
TO_TIMESTAMP('15/10/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'));

INSERT INTO cabenh VALUES 
('CA026','BN026', 'BS003', 'BE001', 'Nang', 'Nhap vien',   
TO_TIMESTAMP('16/09/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),
TO_TIMESTAMP('15/01/2024 00:00:00', 'DD/MM/YYYY HH24:MI:SS'), 
'Dieu tri', 'PH113', 
TO_TIMESTAMP('16/09/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS')); 

INSERT INTO cabenh VALUES
('CA027','BN027', 'BS008', 'BE009', 'Khong cap cuu', 'Tai gia',
TO_TIMESTAMP('18/10/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),
TO_TIMESTAMP('15/01/2024 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),
'Giam sat', NULL,   
TO_TIMESTAMP('18/10/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'));

INSERT INTO cabenh VALUES  
('CA028','BN028', 'BS014', 'BE019', 'Hoi suc', 'Nhap vien',
TO_TIMESTAMP('20/10/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),
TO_TIMESTAMP('15/01/2024 00:00:00', 'DD/MM/YYYY HH24:MI:SS'), 
'Cham soc', 'PH114',
TO_TIMESTAMP('20/10/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'));

INSERT INTO cabenh VALUES
('CA029','BN029', 'BS010', 'BE020', 'Cham soc dac biet', 'Nhap vien',   
TO_TIMESTAMP('12/10/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),
TO_TIMESTAMP('15/01/2024 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),  
'Trieu chung', 'PH115',
TO_TIMESTAMP('12/10/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS')); 

INSERT INTO cabenh VALUES
('CA030','BN030', 'BS017', 'BE014', 'Cap cuu', 'Nhap vien', 
TO_TIMESTAMP('18/10/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),
TO_TIMESTAMP('15/12/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),
'Dieu tri', 'PH116',
TO_TIMESTAMP('18/10/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'));

INSERT INTO cabenh VALUES
('CA031','BN031', 'BS013', 'BE016', 'Khong cap cuu', 'Nhap vien',  
TO_TIMESTAMP('15/10/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),
TO_TIMESTAMP('15/12/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'), 
'Giam sat', 'PH117',
TO_TIMESTAMP('15/10/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'));

INSERT INTO cabenh VALUES
('CA032','BN032', 'BS009', 'BE004', 'Cham soc dac biet', 'Tai gia', 
TO_TIMESTAMP('20/10/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),
TO_TIMESTAMP('15/12/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),
'Trieu chung', NULL,  
TO_TIMESTAMP('20/10/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'));

INSERT INTO cabenh VALUES
('CA033','BN033', 'BS012', 'BE011', 'Hoi suc', 'Nhap vien',  
TO_TIMESTAMP('12/09/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),
TO_TIMESTAMP('15/12/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),  
'Dieu tri', 'PH118',
TO_TIMESTAMP('12/09/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'));

INSERT INTO cabenh VALUES 
('CA034','BN034', 'BS002', 'BE021', 'Cap cuu', 'Tai gia',
TO_TIMESTAMP('17/09/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),
TO_TIMESTAMP('15/01/2024 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),
'Chuan doan', NULL, 
TO_TIMESTAMP('17/09/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'));  

INSERT INTO cabenh VALUES
('CA035','BN035', 'BS017', 'BE012', 'Nang', 'Nhap vien',  
TO_TIMESTAMP('20/10/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),
TO_TIMESTAMP('15/01/2024 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),
'Giam sat', 'PH119', 
TO_TIMESTAMP('20/10/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'));

INSERT INTO cabenh VALUES  
('CA036','BN036', 'BS008', 'BE010', 'Cham soc dac biet', 'Cach ly',
TO_TIMESTAMP('15/09/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),  
TO_TIMESTAMP('15/12/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),
'Chuan doan', 'PH304',
TO_TIMESTAMP('15/09/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'));

INSERT INTO cabenh VALUES
('CA037','BN037', 'BS010', 'BE019', 'Khong cap cuu', 'Tai gia',   
TO_TIMESTAMP('11/10/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),
TO_TIMESTAMP('15/01/2024 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),
'Trieu chung', NULL, 
TO_TIMESTAMP('11/10/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'));  

INSERT INTO cabenh VALUES
('CA038','BN038', 'BS004', 'BE002', 'Hoi suc', 'Nhap vien',
TO_TIMESTAMP('16/09/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),
TO_TIMESTAMP('15/12/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'), 
'Giam sat', 'PH120',  
TO_TIMESTAMP('16/09/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'));

INSERT INTO cabenh VALUES
('CA039','BN039', 'BS007', 'BE023', 'Cap cuu', 'Tai gia',  
TO_TIMESTAMP('20/10/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),
TO_TIMESTAMP('15/12/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),
'Chuan doan', NULL,  
TO_TIMESTAMP('20/10/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'));  

INSERT INTO cabenh VALUES
('CA040','BN040', 'BS019', 'BE005', 'Nang', 'Nhap vien',   
TO_TIMESTAMP('18/10/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),
TO_TIMESTAMP('15/01/2024 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),  
'Dieu tri', 'PH201',
TO_TIMESTAMP('18/10/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'));

INSERT INTO cabenh VALUES
('CA041','BN041', 'BS014', 'BE017', 'Khong cap cuu', 'Nhap vien',
TO_TIMESTAMP('15/10/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),
TO_TIMESTAMP('15/12/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),   
'Cham soc', 'PH202', 
TO_TIMESTAMP('15/10/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'));   

INSERT INTO cabenh VALUES
('CA042','BN042', 'BS011', 'BE018', 'Hoi suc', 'Nhap vien',   
TO_TIMESTAMP('20/10/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),  
TO_TIMESTAMP('12/01/2024 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),
'Trieu chung', 'PH203',  
TO_TIMESTAMP('20/10/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS')); 

INSERT INTO cabenh VALUES
('CA043','BN043', 'BS006', 'BE024', 'Cap cuu', 'Tai gia',   
TO_TIMESTAMP('18/09/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),
TO_TIMESTAMP('15/01/2024 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),  
'Chuan doan', NULL, 
TO_TIMESTAMP('18/09/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'));

INSERT INTO cabenh VALUES 
('CA044','BN044', 'BS018', 'BE022', 'Nang', 'Nhap vien',   
TO_TIMESTAMP('17/10/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),
TO_TIMESTAMP('15/01/2024 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),
'Dieu tri', 'PH204',
TO_TIMESTAMP('17/10/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS')); 

INSERT INTO cabenh VALUES
('CA045','BN045', 'BS016', 'BE005', 'Cham soc dac biet', 'Tai gia',  
TO_TIMESTAMP('15/10/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),
TO_TIMESTAMP('15/12/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),  
'Giam sat', NULL,  
TO_TIMESTAMP('15/10/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'));

INSERT INTO cabenh VALUES
('CA046','BN046', 'BS005', 'BE025', 'Khong cap cuu', 'Nhap vien', 
TO_TIMESTAMP('15/09/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),
TO_TIMESTAMP('15/12/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),
'Chuan doan', 'PH205',
TO_TIMESTAMP('15/09/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'));

INSERT INTO cabenh VALUES 
('CA047','BN047', 'BS001', 'BE013', 'Hoi suc', 'Nhap vien',  
TO_TIMESTAMP('14/10/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),
TO_TIMESTAMP('15/01/2024 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),
'Trieu chung', 'PH206', 
TO_TIMESTAMP('14/10/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS')); 

INSERT INTO cabenh VALUES
('CA048','BN048', 'BS015', 'BE014', 'Cap cuu', 'Tai gia',  
TO_TIMESTAMP('20/10/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),
TO_TIMESTAMP('15/01/2024 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),
'Dieu tri', NULL, 
TO_TIMESTAMP('20/10/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'));

INSERT INTO cabenh VALUES  
('CA049','BN049', 'BS010', 'BE020', 'Nang', 'Nhap vien', 
TO_TIMESTAMP('11/10/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),
TO_TIMESTAMP('15/01/2024 00:00:00', 'DD/MM/YYYY HH24:MI:SS'), 
'Giam sat', 'PH207',
TO_TIMESTAMP('11/10/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'));  

INSERT INTO cabenh VALUES
('CA050','BN050', 'BS008', 'BE009', 'Cham soc dac biet', 'Cach ly',   
TO_TIMESTAMP('15/09/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'), 
TO_TIMESTAMP('15/12/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'),
'Chuan doan', 'PH305',  
TO_TIMESTAMP('15/09/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'));

COMMIT;

INSERT INTO thietbiyte VALUES ('TB001', 'Nhiet ke', 'Tai su dung', 'Do nhiet do co the', 1201, 1201, 0);
INSERT INTO thietbiyte VALUES ('TB002', 'May huyet ap', 'Tai su dung', 'Do huyet ap', 145, 145, 20000);
INSERT INTO thietbiyte VALUES ('TB003', 'Binh oxy 10 lit', '1 lan', 'Cung cap oxy', 546, 546, 1500000);
INSERT INTO thietbiyte VALUES ('TB004', 'May chup X-quang', 'Tai su dung', 'Tao hinh anh X-quang', 55, 55, 150000);
INSERT INTO thietbiyte VALUES ('TB005', 'May sieu am', 'Tai su dung', 'Tao hinh anh sieu am', 43, 43, 300000);
INSERT INTO thietbiyte VALUES ('TB006', 'May ECG', 'Tai su dung', 'Do hoat dong dien cua tim', 237, 237, 50000);
INSERT INTO thietbiyte VALUES ('TB007', 'May chay than', 'Tai su dung', 'Chay than cap cuu va loc mau', 378, 378, 800000);
INSERT INTO thietbiyte VALUES ('TB008', 'Dao phau thuat', 'Tai su dung', 'Cat va mo', 878, 878, 0);
INSERT INTO thietbiyte VALUES ('TB009', 'May noi soi tieu hoa', 'Tai su dung', 'Kiem tra suc khoe duong tieu hoa', 179, 179, 2400000);
INSERT INTO thietbiyte VALUES ('TB010', 'May ho hap nhan tao', 'Tai su dung', 'Ho tro ho hap', 412, 412, 1500000);
INSERT INTO thietbiyte VALUES ('TB011', 'Ong tiem', '1 lan', 'Tiem thuoc cho benh nhan', 412, 412, 2000);

COMMIT;

INSERT INTO dieuphoithietbi VALUES 
('CA001', 'TB002', 1, 
 TO_TIMESTAMP('15/09/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'),
 TO_TIMESTAMP('01/12/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));

INSERT INTO dieuphoithietbi VALUES 
('CA001', 'TB006', 1,
TO_TIMESTAMP('18/09/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), 
TO_TIMESTAMP('25/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));

INSERT INTO dieuphoithietbi VALUES
('CA001', 'TB007', 1,  
TO_TIMESTAMP('20/09/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'),
TO_TIMESTAMP('27/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));  

INSERT INTO dieuphoithietbi VALUES
('CA001', 'TB011', 5, 
TO_TIMESTAMP('22/09/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'),
TO_TIMESTAMP('29/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));

INSERT INTO dieuphoithietbi VALUES  
('CA002', 'TB001', 4,
TO_TIMESTAMP('15/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'),  
TO_TIMESTAMP('25/12/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));

INSERT INTO dieuphoithietbi VALUES
('CA002', 'TB002', 1, 
TO_TIMESTAMP('17/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'),
TO_TIMESTAMP('27/12/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));

INSERT INTO dieuphoithietbi VALUES 
('CA002', 'TB011', 5,
TO_TIMESTAMP('12/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'),  
TO_TIMESTAMP('20/12/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));

INSERT INTO dieuphoithietbi VALUES
('CA003', 'TB010', 1,  
TO_TIMESTAMP('15/09/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'),
TO_TIMESTAMP('22/09/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));

INSERT INTO dieuphoithietbi VALUES  
('CA004', 'TB001', 4,
TO_TIMESTAMP('20/09/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), 
TO_TIMESTAMP('28/09/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));

INSERT INTO dieuphoithietbi VALUES
('CA004', 'TB007', 3,  
TO_TIMESTAMP('22/09/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'),
TO_TIMESTAMP('30/09/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));

INSERT INTO dieuphoithietbi VALUES
('CA005', 'TB004', 1,  
TO_TIMESTAMP('25/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'),
TO_TIMESTAMP('02/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS')); 

INSERT INTO dieuphoithietbi VALUES
('CA006', 'TB003', 5,  
TO_TIMESTAMP('18/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'),
TO_TIMESTAMP('25/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));

INSERT INTO dieuphoithietbi VALUES 
('CA006', 'TB009', 1,
TO_TIMESTAMP('20/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'),
TO_TIMESTAMP('27/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));

INSERT INTO dieuphoithietbi VALUES
('CA007', 'TB003', 4,  
TO_TIMESTAMP('15/09/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), 
TO_TIMESTAMP('22/09/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));

INSERT INTO dieuphoithietbi VALUES
('CA007', 'TB005', 1,   
TO_TIMESTAMP('18/09/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'),
TO_TIMESTAMP('25/09/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));

INSERT INTO dieuphoithietbi VALUES  
('CA007', 'TB009', 1,
TO_TIMESTAMP('20/09/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'),
TO_TIMESTAMP('27/09/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));

INSERT INTO dieuphoithietbi VALUES
('CA007', 'TB010', 1,  
TO_TIMESTAMP('22/09/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), 
TO_TIMESTAMP('29/09/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));

INSERT INTO dieuphoithietbi VALUES
('CA008', 'TB002', 1,  
TO_TIMESTAMP('20/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'),
TO_TIMESTAMP('27/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));

INSERT INTO dieuphoithietbi VALUES  
('CA008', 'TB009', 1,
TO_TIMESTAMP('22/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), 
TO_TIMESTAMP('29/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS')); 

INSERT INTO dieuphoithietbi VALUES
('CA008', 'TB010', 1,   
TO_TIMESTAMP('25/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'),
TO_TIMESTAMP('01/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));

INSERT INTO dieuphoithietbi VALUES  
('CA008', 'TB011', 4, 
TO_TIMESTAMP('27/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'),
TO_TIMESTAMP('03/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));   

INSERT INTO dieuphoithietbi VALUES 
('CA009', 'TB002', 1, 
TO_TIMESTAMP('03/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'),
TO_TIMESTAMP('10/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));

INSERT INTO dieuphoithietbi VALUES
('CA009', 'TB009', 1,  
TO_TIMESTAMP('05/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), 
TO_TIMESTAMP('12/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));

INSERT INTO dieuphoithietbi VALUES
('CA010', 'TB005', 1,  
TO_TIMESTAMP('15/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'),
TO_TIMESTAMP('20/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));

INSERT INTO dieuphoithietbi VALUES
('CA011', 'TB008', 5,   
TO_TIMESTAMP('18/09/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'),  
TO_TIMESTAMP('25/09/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));

INSERT INTO dieuphoithietbi VALUES
('CA012', 'TB003', 4,  
TO_TIMESTAMP('20/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'),
TO_TIMESTAMP('27/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));

INSERT INTO dieuphoithietbi VALUES 
('CA012', 'TB005', 1,
TO_TIMESTAMP('22/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), 
TO_TIMESTAMP('29/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));  

INSERT INTO dieuphoithietbi VALUES
('CA012', 'TB010', 1,  
TO_TIMESTAMP('25/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'),
TO_TIMESTAMP('01/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));

INSERT INTO dieuphoithietbi VALUES 
('CA013', 'TB001', 3,
TO_TIMESTAMP('20/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'),  
TO_TIMESTAMP('27/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));   

INSERT INTO dieuphoithietbi VALUES
('CA013', 'TB003', 4,
TO_TIMESTAMP('22/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), 
TO_TIMESTAMP('29/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));

INSERT INTO dieuphoithietbi VALUES
('CA013', 'TB006', 1,  
TO_TIMESTAMP('25/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'),
TO_TIMESTAMP('01/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));  

INSERT INTO dieuphoithietbi VALUES
('CA014', 'TB006', 1,  
TO_TIMESTAMP('18/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'),
TO_TIMESTAMP('25/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));

INSERT INTO dieuphoithietbi VALUES 
('CA015', 'TB004', 1,  
TO_TIMESTAMP('18/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'),
TO_TIMESTAMP('25/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));  

INSERT INTO dieuphoithietbi VALUES
('CA015', 'TB007', 2,
TO_TIMESTAMP('20/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'),  
TO_TIMESTAMP('27/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));   

INSERT INTO dieuphoithietbi VALUES  
('CA016', 'TB007', 5,  
TO_TIMESTAMP('12/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'),
TO_TIMESTAMP('19/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));   

INSERT INTO dieuphoithietbi VALUES
('CA016', 'TB008', 2,  
TO_TIMESTAMP('15/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'),  
TO_TIMESTAMP('22/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));

INSERT INTO dieuphoithietbi VALUES
('CA016', 'TB010', 1,  
TO_TIMESTAMP('17/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'),  
TO_TIMESTAMP('24/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));

INSERT INTO dieuphoithietbi VALUES 
('CA017', 'TB002', 1,
TO_TIMESTAMP('18/09/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'),  
TO_TIMESTAMP('22/09/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));  

INSERT INTO dieuphoithietbi VALUES 
('CA017', 'TB008', 2,  
TO_TIMESTAMP('20/09/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), 
TO_TIMESTAMP('24/09/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));  

INSERT INTO dieuphoithietbi VALUES
('CA018', 'TB001', 1,  
TO_TIMESTAMP('02/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'),  
TO_TIMESTAMP('09/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));

INSERT INTO dieuphoithietbi VALUES  
('CA018', 'TB004', 1, 
TO_TIMESTAMP('04/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'),
TO_TIMESTAMP('11/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));  

INSERT INTO dieuphoithietbi VALUES
('CA018', 'TB006', 1,  
TO_TIMESTAMP('06/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'),  
TO_TIMESTAMP('13/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));

INSERT INTO dieuphoithietbi VALUES  
('CA019', 'TB001', 5, 
TO_TIMESTAMP('18/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'),  
TO_TIMESTAMP('15/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));   

INSERT INTO dieuphoithietbi VALUES  
('CA019', 'TB002', 1,
TO_TIMESTAMP('20/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'),  
TO_TIMESTAMP('17/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));  

INSERT INTO dieuphoithietbi VALUES  
('CA019', 'TB010', 1, 
TO_TIMESTAMP('22/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'),
TO_TIMESTAMP('19/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));

INSERT INTO dieuphoithietbi VALUES
('CA020', 'TB006', 1,  
TO_TIMESTAMP('15/09/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'),
TO_TIMESTAMP('20/09/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));

INSERT INTO dieuphoithietbi VALUES
('CA021', 'TB003', 2,  
TO_TIMESTAMP('20/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'),
TO_TIMESTAMP('27/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));  

INSERT INTO dieuphoithietbi VALUES
('CA022', 'TB003', 1,  
TO_TIMESTAMP('22/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'),
TO_TIMESTAMP('29/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));  

INSERT INTO dieuphoithietbi VALUES
('CA022', 'TB005', 1,   
TO_TIMESTAMP('25/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'),
TO_TIMESTAMP('01/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));

INSERT INTO dieuphoithietbi VALUES  
('CA022', 'TB009', 1,  
TO_TIMESTAMP('27/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'),
TO_TIMESTAMP('03/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));

INSERT INTO dieuphoithietbi VALUES
('CA022', 'TB011', 2,   
TO_TIMESTAMP('30/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'),
TO_TIMESTAMP('06/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));

INSERT INTO dieuphoithietbi VALUES  
('CA023', 'TB003', 3,  
TO_TIMESTAMP('18/09/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'),
TO_TIMESTAMP('23/09/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));

INSERT INTO dieuphoithietbi VALUES
('CA023', 'TB005', 1,   
TO_TIMESTAMP('20/09/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), 
TO_TIMESTAMP('25/09/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));

INSERT INTO dieuphoithietbi VALUES
('CA023', 'TB009', 1,    
TO_TIMESTAMP('22/09/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'),
TO_TIMESTAMP('27/09/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));

INSERT INTO dieuphoithietbi VALUES  
('CA024', 'TB001', 4,  
TO_TIMESTAMP('18/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), 
TO_TIMESTAMP('25/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));  

INSERT INTO dieuphoithietbi VALUES  
('CA024', 'TB002', 1, 
TO_TIMESTAMP('20/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'),   
TO_TIMESTAMP('27/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));

INSERT INTO dieuphoithietbi VALUES
('CA024', 'TB006', 1,  
TO_TIMESTAMP('22/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'),   
TO_TIMESTAMP('29/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));

INSERT INTO dieuphoithietbi VALUES
('CA024', 'TB009', 1,    
TO_TIMESTAMP('25/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'),  
TO_TIMESTAMP('01/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));

INSERT INTO dieuphoithietbi VALUES 
('CA025', 'TB006', 1, 
TO_TIMESTAMP('15/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'),  
TO_TIMESTAMP('20/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));

INSERT INTO dieuphoithietbi VALUES 
('CA025', 'TB010', 1,  
TO_TIMESTAMP('18/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'),
TO_TIMESTAMP('23/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));

INSERT INTO dieuphoithietbi VALUES
('CA026', 'TB009', 1,   
TO_TIMESTAMP('18/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), 
TO_TIMESTAMP('23/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS')); 

INSERT INTO dieuphoithietbi VALUES 
('CA026', 'TB010', 1,  
TO_TIMESTAMP('20/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'),
TO_TIMESTAMP('25/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));  

INSERT INTO dieuphoithietbi VALUES 
('CA027', 'TB001', 3, 
TO_TIMESTAMP('20/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'),  
TO_TIMESTAMP('25/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));   

INSERT INTO dieuphoithietbi VALUES  
('CA027', 'TB006', 1,
TO_TIMESTAMP('22/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'),  
TO_TIMESTAMP('27/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));

INSERT INTO dieuphoithietbi VALUES 
('CA027', 'TB010', 1,   
TO_TIMESTAMP('25/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'),
TO_TIMESTAMP('30/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));

INSERT INTO dieuphoithietbi VALUES 
('CA028', 'TB001', 2, 
TO_TIMESTAMP('20/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'),  
TO_TIMESTAMP('25/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS')); 

INSERT INTO dieuphoithietbi VALUES
('CA028', 'TB004', 1,  
TO_TIMESTAMP('22/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'),
TO_TIMESTAMP('27/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));

INSERT INTO dieuphoithietbi VALUES  
('CA028', 'TB005', 1,  
TO_TIMESTAMP('25/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'),
TO_TIMESTAMP('30/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));  

INSERT INTO dieuphoithietbi VALUES  
('CA028', 'TB008', 3, 
TO_TIMESTAMP('27/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'),  
TO_TIMESTAMP('01/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));  

INSERT INTO dieuphoithietbi VALUES 
('CA029', 'TB001', 1,
TO_TIMESTAMP('14/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'),  
TO_TIMESTAMP('20/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));

INSERT INTO dieuphoithietbi VALUES
('CA029', 'TB006', 1,   
TO_TIMESTAMP('16/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'),
TO_TIMESTAMP('22/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));

INSERT INTO dieuphoithietbi VALUES  
('CA029', 'TB010', 1, 
TO_TIMESTAMP('18/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), 
TO_TIMESTAMP('24/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));

INSERT INTO dieuphoithietbi VALUES ('CA030', 'TB001', 1, TO_TIMESTAMP('13/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), TO_TIMESTAMP('17/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));
INSERT INTO dieuphoithietbi VALUES ('CA030', 'TB004', 1, TO_TIMESTAMP('13/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), TO_TIMESTAMP('17/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));
INSERT INTO dieuphoithietbi VALUES ('CA030', 'TB005', 1, TO_TIMESTAMP('13/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), TO_TIMESTAMP('17/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));
INSERT INTO dieuphoithietbi VALUES ('CA030', 'TB010', 1, TO_TIMESTAMP('13/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), TO_TIMESTAMP('17/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));
INSERT INTO dieuphoithietbi VALUES ('CA031', 'TB001', 4, TO_TIMESTAMP('16/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), TO_TIMESTAMP('20/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));
INSERT INTO dieuphoithietbi VALUES ('CA031', 'TB009', 1, TO_TIMESTAMP('16/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), TO_TIMESTAMP('20/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));
INSERT INTO dieuphoithietbi VALUES ('CA032', 'TB003', 1, TO_TIMESTAMP('30/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), TO_TIMESTAMP('02/12/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));
INSERT INTO dieuphoithietbi VALUES ('CA032', 'TB005', 1, TO_TIMESTAMP('30/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), TO_TIMESTAMP('02/12/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));
INSERT INTO dieuphoithietbi VALUES ('CA032', 'TB007', 5, TO_TIMESTAMP('30/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), TO_TIMESTAMP('02/12/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));
INSERT INTO dieuphoithietbi VALUES ('CA032', 'TB009', 1, TO_TIMESTAMP('30/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), TO_TIMESTAMP('02/12/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));
INSERT INTO dieuphoithietbi VALUES ('CA033', 'TB001', 1, TO_TIMESTAMP('03/12/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), TO_TIMESTAMP('09/12/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));
INSERT INTO dieuphoithietbi VALUES ('CA033', 'TB004', 1, TO_TIMESTAMP('03/12/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), TO_TIMESTAMP('09/12/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));
INSERT INTO dieuphoithietbi VALUES ('CA033', 'TB007', 4, TO_TIMESTAMP('03/12/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), TO_TIMESTAMP('09/12/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));
INSERT INTO dieuphoithietbi VALUES ('CA033', 'TB008', 1, TO_TIMESTAMP('03/12/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), TO_TIMESTAMP('09/12/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));
INSERT INTO dieuphoithietbi VALUES ('CA034', 'TB001', 2, TO_TIMESTAMP('10/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), TO_TIMESTAMP('14/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));
INSERT INTO dieuphoithietbi VALUES ('CA034', 'TB004', 1, TO_TIMESTAMP('10/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), TO_TIMESTAMP('14/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));
INSERT INTO dieuphoithietbi VALUES ('CA034', 'TB011', 3, TO_TIMESTAMP('10/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), TO_TIMESTAMP('14/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));
INSERT INTO dieuphoithietbi VALUES ('CA035', 'TB003', 1, TO_TIMESTAMP('22/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), TO_TIMESTAMP('28/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));
INSERT INTO dieuphoithietbi VALUES ('CA035', 'TB004', 1, TO_TIMESTAMP('22/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), TO_TIMESTAMP('28/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));
INSERT INTO dieuphoithietbi VALUES ('CA035', 'TB010', 1, TO_TIMESTAMP('22/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), TO_TIMESTAMP('28/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));
INSERT INTO dieuphoithietbi VALUES ('CA036', 'TB007', 5, TO_TIMESTAMP('07/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), TO_TIMESTAMP('12/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));
INSERT INTO dieuphoithietbi VALUES ('CA036', 'TB008', 5, TO_TIMESTAMP('07/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), TO_TIMESTAMP('12/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));
INSERT INTO dieuphoithietbi VALUES ('CA037', 'TB002', 1, TO_TIMESTAMP('20/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), TO_TIMESTAMP('26/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));
INSERT INTO dieuphoithietbi VALUES ('CA037', 'TB006', 1, TO_TIMESTAMP('20/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), TO_TIMESTAMP('26/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));
INSERT INTO dieuphoithietbi VALUES ('CA037', 'TB010', 1, TO_TIMESTAMP('20/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), TO_TIMESTAMP('26/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));
INSERT INTO dieuphoithietbi VALUES ('CA038', 'TB004', 1, TO_TIMESTAMP('03/12/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), TO_TIMESTAMP('10/12/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));
INSERT INTO dieuphoithietbi VALUES ('CA038', 'TB007', 1, TO_TIMESTAMP('03/12/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), TO_TIMESTAMP('10/12/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));
INSERT INTO dieuphoithietbi VALUES ('CA039', 'TB008', 5, TO_TIMESTAMP('17/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), TO_TIMESTAMP('23/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));
INSERT INTO dieuphoithietbi VALUES ('CA040', 'TB007', 1, TO_TIMESTAMP('30/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), TO_TIMESTAMP('25/12/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));
INSERT INTO dieuphoithietbi VALUES ('CA040', 'TB008', 2, TO_TIMESTAMP('30/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), TO_TIMESTAMP('25/12/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));
INSERT INTO dieuphoithietbi VALUES ('CA040', 'TB009', 1, TO_TIMESTAMP('30/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), TO_TIMESTAMP('25/12/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));
INSERT INTO dieuphoithietbi VALUES ('CA040', 'TB010', 1, TO_TIMESTAMP('30/10/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), TO_TIMESTAMP('25/12/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));
INSERT INTO dieuphoithietbi VALUES ('CA041', 'TB009', 1, TO_TIMESTAMP('13/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), TO_TIMESTAMP('19/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));
INSERT INTO dieuphoithietbi VALUES ('CA041', 'TB010', 1, TO_TIMESTAMP('13/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), TO_TIMESTAMP('19/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));
INSERT INTO dieuphoithietbi VALUES ('CA042', 'TB004', 1, TO_TIMESTAMP('26/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), TO_TIMESTAMP('01/12/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));
INSERT INTO dieuphoithietbi VALUES ('CA042', 'TB006', 1, TO_TIMESTAMP('26/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), TO_TIMESTAMP('01/12/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));
INSERT INTO dieuphoithietbi VALUES ('CA042', 'TB007', 2, TO_TIMESTAMP('26/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), TO_TIMESTAMP('01/12/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));
INSERT INTO dieuphoithietbi VALUES ('CA042', 'TB011', 5, TO_TIMESTAMP('26/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), TO_TIMESTAMP('01/12/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));
INSERT INTO dieuphoithietbi VALUES ('CA043', 'TB001', 3, TO_TIMESTAMP('09/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), TO_TIMESTAMP('15/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));
INSERT INTO dieuphoithietbi VALUES ('CA043', 'TB004', 1, TO_TIMESTAMP('09/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), TO_TIMESTAMP('15/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));
INSERT INTO dieuphoithietbi VALUES ('CA043', 'TB007', 4, TO_TIMESTAMP('09/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), TO_TIMESTAMP('15/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));
INSERT INTO dieuphoithietbi VALUES ('CA043', 'TB008', 1, TO_TIMESTAMP('09/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), TO_TIMESTAMP('15/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));
INSERT INTO dieuphoithietbi VALUES ('CA044', 'TB002', 1, TO_TIMESTAMP('22/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), TO_TIMESTAMP('27/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));
INSERT INTO dieuphoithietbi VALUES ('CA044', 'TB010', 1, TO_TIMESTAMP('22/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), TO_TIMESTAMP('27/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));
INSERT INTO dieuphoithietbi VALUES ('CA045', 'TB001', 3, TO_TIMESTAMP('03/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), TO_TIMESTAMP('09/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));
INSERT INTO dieuphoithietbi VALUES ('CA045', 'TB002', 1, TO_TIMESTAMP('03/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), TO_TIMESTAMP('09/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));
INSERT INTO dieuphoithietbi VALUES ('CA045', 'TB006', 1, TO_TIMESTAMP('03/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), TO_TIMESTAMP('09/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));
INSERT INTO dieuphoithietbi VALUES ('CA045', 'TB010', 1, TO_TIMESTAMP('03/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), TO_TIMESTAMP('09/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));
INSERT INTO dieuphoithietbi VALUES ('CA046', 'TB002', 1, TO_TIMESTAMP('17/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), TO_TIMESTAMP('23/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));
INSERT INTO dieuphoithietbi VALUES ('CA046', 'TB005', 1, TO_TIMESTAMP('17/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), TO_TIMESTAMP('23/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));
INSERT INTO dieuphoithietbi VALUES ('CA047', 'TB003', 3, TO_TIMESTAMP('30/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), TO_TIMESTAMP('03/12/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));
INSERT INTO dieuphoithietbi VALUES ('CA047', 'TB004', 1, TO_TIMESTAMP('30/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), TO_TIMESTAMP('03/12/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));
INSERT INTO dieuphoithietbi VALUES ('CA047', 'TB007', 1, TO_TIMESTAMP('30/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), TO_TIMESTAMP('03/12/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));
INSERT INTO dieuphoithietbi VALUES ('CA047', 'TB011', 1, TO_TIMESTAMP('30/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), TO_TIMESTAMP('03/12/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));
INSERT INTO dieuphoithietbi VALUES ('CA048', 'TB001', 5, TO_TIMESTAMP('11/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), TO_TIMESTAMP('17/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));
INSERT INTO dieuphoithietbi VALUES ('CA048', 'TB007', 4, TO_TIMESTAMP('11/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), TO_TIMESTAMP('17/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));
INSERT INTO dieuphoithietbi VALUES ('CA049', 'TB004', 1, TO_TIMESTAMP('25/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), TO_TIMESTAMP('01/12/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));
INSERT INTO dieuphoithietbi VALUES ('CA050', 'TB002', 1, TO_TIMESTAMP('08/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'), TO_TIMESTAMP('13/11/2023 00:00:00','DD/MM/YYYY HH24:MI:SS'));

COMMIT;


INSERT INTO THUOC VALUES ('TH001', 'Paracetamol', 'Hạ sốt, giảm đau nhẹ', 350, 5000);

INSERT INTO THUOC VALUES ('TH002', 'Ibuprofen', 'Giảm đau, hạ sốt, chống viêm', 250, 8000);

INSERT INTO THUOC VALUES ('TH003', 'Omeprazole', 'Ức chế tiết axit dạ dày', 150, 12000);

INSERT INTO THUOC VALUES ('TH004', 'Ambroxol', 'Long đờm, ho, viêm đường hô hấp', 500, 3000); 

INSERT INTO THUOC VALUES ('TH005', 'Vitamin C', 'Bổ sung vitamin C, tăng cường hệ miễn dịch', 430, 4000);

INSERT INTO THUOC VALUES ('TH006', 'Amoxicillin', 'Kháng sinh, điều trị nhiễm trùng', 120, 15000);

INSERT INTO THUOC VALUES ('TH007', 'Atorvastatin', 'Hạ cholesterol máu', 230, 22000);

INSERT INTO THUOC VALUES ('TH008', 'Salbutamol', 'Giãn phế quản, điều trị hen suyễn', 210, 10000);

INSERT INTO THUOC VALUES ('TH009', ' Vitamin B1', 'Bổ sung vitamin B1', 500, 8000); 

INSERT INTO THUOC VALUES ('TH010', 'Aspirin', 'Hạ sốt, giảm đau, chống viêm', 400, 6000);

INSERT INTO THUOC VALUES ('TH011', 'Diazepam', 'Thuốc an thần, chống co giật', 180, 15000);

INSERT INTO THUOC VALUES ('TH012', 'Doxycycline ', 'Kháng sinh phổ rộng', 290, 22000);  

INSERT INTO THUOC VALUES ('TH013', 'Enalapril', 'Thuốc hạ huyết áp', 340, 12000);

INSERT INTO THUOC VALUES ('TH014', 'Fluconazole', 'Thuốc điều trị nấm', 270, 20000);    

INSERT INTO THUOC VALUES ('TH015', 'Furosemide ', 'Thuốc lợi tiểu', 420, 5000);

INSERT INTO THUOC VALUES ('TH016', 'Gliclazide ', 'Thuốc điều trị đái tháo đường', 510, 14000);  

INSERT INTO THUOC VALUES ('TH017', 'Hyoscine', 'Thuốc chống nôn nhiễm', 250, 10000);   

INSERT INTO THUOC VALUES ('TH018', 'Ipratropium', 'Thuốc hen suyễn và COPD', 180, 17000);

INSERT INTO THUOC VALUES ('TH019', 'Lansoprazole ', 'Thuốc ức chế acid dạ dày', 200, 18000);

INSERT INTO THUOC VALUES ('TH020', 'Miconazole ', 'Thuốc điều trị nấm da', 340, 12000);  

INSERT INTO THUOC VALUES ('TH021', 'Naproxen', 'Giảm đau, chống viêm', 290, 8000);

INSERT INTO THUOC VALUES ('TH022', 'Ondansetron ', 'Thuốc chống nôn', 160, 25000);   

INSERT INTO THUOC VALUES ('TH023', 'Prednisolone', 'Corticoid giảm viêm & ức chế miễn dịch', 220, 16000);

INSERT INTO THUOC VALUES ('TH024', 'Queiapine', 'Thuốc an thần, ổn định tâm trạng', 140, 20000);

INSERT INTO THUOC VALUES ('TH025', 'Ranitidine', 'Thuốc kháng acid', 190, 9000); 

INSERT INTO THUOC VALUES ('TH026', 'Sertraline', 'Thuốc chống trầm cảm', 270, 17000);

INSERT INTO THUOC VALUES ('TH027', 'Tamsulosin', 'Thuốc điều trị u xơ tuyến tiền liệt', 210, 22000);

INSERT INTO THUOC VALUES ('TH028', 'Pantoprazole ', 'Thuốc ức chế acid dạ dày', 180, 21000);

INSERT INTO THUOC VALUES ('TH029', 'Methocarbamol', 'Thuốc giảm đau cơ', 140, 12000);

INSERT INTO THUOC VALUES ('TH030', 'Zolpidem', 'Thuốc an thần, thuốc ngủ', 120, 14000);

commit;


INSERT INTO KETHUOC (MACA, MATHUOC, NGAYKE, SL) VALUES
('CA003', 'TH015', TO_TIMESTAMP('03/11/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'), 8);
INSERT INTO KETHUOC (MACA, MATHUOC, NGAYKE, SL) VALUES
('CA025', 'TH027', TO_TIMESTAMP('14/11/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'), 15);
INSERT INTO KETHUOC (MACA, MATHUOC, NGAYKE, SL) VALUES
('CA049', 'TH003', TO_TIMESTAMP('07/11/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'), 5);
INSERT INTO KETHUOC (MACA, MATHUOC, NGAYKE, SL) VALUES
('CA012', 'TH020', TO_TIMESTAMP('18/11/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'), 12);
INSERT INTO KETHUOC (MACA, MATHUOC, NGAYKE, SL) VALUES
('CA036', 'TH009', TO_TIMESTAMP('11/11/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'), 10);
INSERT INTO KETHUOC (MACA, MATHUOC, NGAYKE, SL) VALUES
('CA028', 'TH025', TO_TIMESTAMP('22/11/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'), 7);
INSERT INTO KETHUOC (MACA, MATHUOC, NGAYKE, SL) VALUES
('CA007', 'TH012', TO_TIMESTAMP('05/11/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'), 18);
INSERT INTO KETHUOC (MACA, MATHUOC, NGAYKE, SL) VALUES
('CA045', 'TH030', TO_TIMESTAMP('28/11/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'), 4);
INSERT INTO KETHUOC (MACA, MATHUOC, NGAYKE, SL) VALUES
('CA016', 'TH006', TO_TIMESTAMP('09/11/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'), 20);
INSERT INTO KETHUOC (MACA, MATHUOC, NGAYKE, SL) VALUES
('CA042', 'TH018', TO_TIMESTAMP('01/11/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'), 13);
INSERT INTO KETHUOC (MACA, MATHUOC, NGAYKE, SL) VALUES
('CA018', 'TH008', TO_TIMESTAMP('13/11/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'), 14);
INSERT INTO KETHUOC (MACA, MATHUOC, NGAYKE, SL) VALUES
('CA033', 'TH022', TO_TIMESTAMP('17/11/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'), 6);
INSERT INTO KETHUOC (MACA, MATHUOC, NGAYKE, SL) VALUES
('CA002', 'TH010', TO_TIMESTAMP('19/11/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'), 9);
INSERT INTO KETHUOC (MACA, MATHUOC, NGAYKE, SL) VALUES
('CA040', 'TH028', TO_TIMESTAMP('23/11/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'), 11);
INSERT INTO KETHUOC (MACA, MATHUOC, NGAYKE, SL) VALUES
('CA022', 'TH016', TO_TIMESTAMP('08/11/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'), 16);
INSERT INTO KETHUOC (MACA, MATHUOC, NGAYKE, SL) VALUES
('CA046', 'TH005', TO_TIMESTAMP('12/11/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'), 3);
INSERT INTO KETHUOC (MACA, MATHUOC, NGAYKE, SL) VALUES
('CA014', 'TH023', TO_TIMESTAMP('21/11/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'), 19);
INSERT INTO KETHUOC (MACA, MATHUOC, NGAYKE, SL) VALUES
('CA032', 'TH014', TO_TIMESTAMP('04/11/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'), 8);
INSERT INTO KETHUOC (MACA, MATHUOC, NGAYKE, SL) VALUES
('CA044', 'TH029', TO_TIMESTAMP('16/11/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'), 7);
INSERT INTO KETHUOC (MACA, MATHUOC, NGAYKE, SL) VALUES
('CA009', 'TH019', TO_TIMESTAMP('26/11/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'), 5);
INSERT INTO KETHUOC (MACA, MATHUOC, NGAYKE, SL) VALUES
('CA024', 'TH002', TO_TIMESTAMP('29/11/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'), 12);
INSERT INTO KETHUOC (MACA, MATHUOC, NGAYKE, SL) VALUES
('CA037', 'TH024', TO_TIMESTAMP('10/11/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'), 18);
INSERT INTO KETHUOC (MACA, MATHUOC, NGAYKE, SL) VALUES
('CA048', 'TH007', TO_TIMESTAMP('02/11/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'), 15);
INSERT INTO KETHUOC (MACA, MATHUOC, NGAYKE, SL) VALUES
('CA020', 'TH026', TO_TIMESTAMP('15/11/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'), 10);
INSERT INTO KETHUOC (MACA, MATHUOC, NGAYKE, SL) VALUES
('CA004', 'TH011', TO_TIMESTAMP('20/11/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'), 13);
INSERT INTO KETHUOC (MACA, MATHUOC, NGAYKE, SL) VALUES
('CA030', 'TH021', TO_TIMESTAMP('07/11/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'), 4);
INSERT INTO KETHUOC (MACA, MATHUOC, NGAYKE, SL) VALUES
('CA013', 'TH013', TO_TIMESTAMP('25/11/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'), 16);
INSERT INTO KETHUOC (MACA, MATHUOC, NGAYKE, SL) VALUES
('CA041', 'TH001', TO_TIMESTAMP('05/11/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'), 9);
INSERT INTO KETHUOC (MACA, MATHUOC, NGAYKE, SL) VALUES
('CA015', 'TH027', TO_TIMESTAMP('18/11/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'), 11);
INSERT INTO KETHUOC (MACA, MATHUOC, NGAYKE, SL) VALUES
('CA038', 'TH017', TO_TIMESTAMP('30/11/2023 00:00:00', 'DD/MM/YYYY HH24:MI:SS'), 14);

commit;