-- 0
create or replace function func_phongbenh_tinhsisophong( PHMaPhong PHONGBENH.MAPHONG%TYPE)
return INT
as
    siso INT := 0;
begin
    SELECT COUNT(DISTINCT(MABN)) INTO siso FROM CABENH
    WHERE MAPHONG = PHMaPhong AND TINHTRANG != 'Da ket thuc';
    return siso;
end;
/
-- 1
create or replace function func_thietbiyte_tinhsothietbidieuphoi( TBMaThietBi THIETBIYTE.MATHIETBI%TYPE)
return INT
as
    tongso INT := 0;
begin
    SELECT SUM(DP.SOLUONG) INTO tongso FROM DIEUPHOITHIETBI DP, CABENH CB
    WHERE DP.MACA = CB.MACA AND DP.MATHIETBI = TBMaThietBi
    AND CB.TINHTRANG != 'Da ket thuc' AND CURRENT_TIMESTAMP < DP.NGAYKETTHUC AND CURRENT_TIMESTAMP >= DP.NGAYDIEUPHOI;
    IF tongso IS NULL THEN
        RETURN 0;
    END IF;
    commit;
    return tongso;
end;
/
-- 2
create or replace procedure sleep(in_time INT)
as
    now_time DATE;
begin
    SELECT SYSDATE INTO now_time FROM DUAL;
    LOOP
        EXIT WHEN now_time + (in_time * (1/86400)) <= SYSDATE;
    END LOOP;
end;
/
----------------------------------------
-- 3
create or replace procedure proc_bacsi_laybacsi (p_result OUT SYS_REFCURSOR)
as
begin
    OPEN p_result FOR SELECT * FROM BACSI ORDER BY MABS ASC;
end;
/
-- 4
create or replace procedure proc_bacsi_them1bacsi (
                                                    BSMaBS BACSI.MABS%TYPE,
                                                    BSHoTen BACSI.HOTEN%TYPE,
                                                    BSGioiTinh BACSI.GIOITINH%TYPE,
                                                    BSNgaySinh VARCHAR,
                                                    BSQueQuan BACSI.QUEQUAN%TYPE,
                                                    BSNoiOHienTai BACSI.NOIOHIENTAI%TYPE,
                                                    BSTenKhoa BACSI.TENKHOA%TYPE,
                                                    BSNamPhucVu BACSI.NAMPHUCVU%TYPE,
                                                    changedrows OUT INT
                                                    )
as
begin
    INSERT INTO BACSI VALUES (BSMaBS, BSHoTen, BSGioiTinh, TO_DATE(BSNgaySinh,'DD/MM/YYYY'), BSQueQuan, BSNoiOHienTai, BSTenKhoa, BSNamPhucVu);
    changedrows := SQL%ROWCOUNT;
    commit;
end;
/
-- 5

create or replace procedure proc_bacsi_xoa1bacsi (
                                                    BSMaBS BACSI.MABS%TYPE,
                                                    changedrows OUT INT
                                                    )
as
begin
    DELETE FROM BACSI 
        WHERE MABS = BSMaBS;
    changedrows := SQL%ROWCOUNT;
    commit;
end;
/
-- 6
create or replace procedure proc_bacsi_sua1bacsi (
                                                    BSMaBS BACSI.MABS%TYPE,
                                                    BSHoTen BACSI.HOTEN%TYPE,
                                                    BSGioiTinh BACSI.GIOITINH%TYPE,
                                                    BSNgaySinh VARCHAR,
                                                    BSQueQuan BACSI.QUEQUAN%TYPE,
                                                    BSNoiOHienTai BACSI.NOIOHIENTAI%TYPE,
                                                    BSTenKhoa BACSI.TENKHOA%TYPE,
                                                    BSNamPhucVu BACSI.NAMPHUCVU%TYPE,
                                                    changedrows OUT INT
                                                    )
as
    v_mabs BACSI.MABS%TYPE;
begin
    SELECT MABS INTO v_mabs FROM BACSI WHERE MABS = BSMaBS;
    UPDATE BACSI 
        SET HOTEN = BSHoTen, GIOITINH = BSGioiTinh, NGAYSINH = TO_DATE(BSNgaySinh,'DD/MM/YYYY'), QUEQUAN = BSQueQuan, NOIOHIENTAI = BSNoiOHienTai, TENKHOA = BSTenKhoa, NAMPHUCVU = BSNamPhucVu
        WHERE MABS = BSMaBS;
    changedrows := SQL%ROWCOUNT;
    commit;
exception
    when no_data_found then
        raise_application_error(-20134, 'Khong ton tai bac si nao voi ma tren');
end;
/
-----------------------------------------
-- 7
create or replace procedure proc_benh_laybenh (p_result OUT SYS_REFCURSOR)
as
begin
    OPEN p_result FOR SELECT * FROM BENH ORDER BY MABENH ASC;
end;
/
-- 8
create or replace procedure proc_benh_them1benh (
                                                    BEMaBenh BENH.MABENH%TYPE,
                                                    BETenBenh BENH.TENBENH%TYPE,
                                                    BETenKhoa BENH.TENKHOA%TYPE,
                                                    BEGia BENH.GIA%TYPE,
                                                    changedrows OUT INT
                                                    )
as
begin
    INSERT INTO BENH VALUES (BEMaBenh, BETenBenh, BETenKhoa, BEGia);
    changedrows := SQL%ROWCOUNT;
    commit;
end;
/
-- 9
create or replace procedure proc_benh_xoa1benh (
                                                    BEMaBenh BENH.MABENH%TYPE,
                                                    changedrows OUT INT
                                                    )
as
begin
    DELETE FROM BENH 
        WHERE MABENH = BEMaBenh;
    changedrows := SQL%ROWCOUNT;
    commit;
end ;
/
-- 10
create or replace procedure proc_benh_sua1benh (
                                                    BEMaBenh BENH.MABENH%TYPE,
                                                    BETenBenh BENH.TENBENH%TYPE,
                                                    BETenKhoa BENH.TENKHOA%TYPE,
                                                    BEGia BENH.GIA%TYPE,
                                                    changedrows OUT INT
                                                    )
as
    v_mabenh BENH.MABENH%TYPE;
begin
    SELECT MABENH INTO v_mabenh FROM BENH WHERE MABENH = BEMaBenh;
    UPDATE BENH 
        SET TENBENH = BETenBenh, TENKHOA = BETenKhoa, GIA = BEGia
        WHERE MABENH = BEMaBenh;
    changedrows := SQL%ROWCOUNT;
    commit;
exception
    when no_data_found then
        raise_application_error(-20135, 'Khong ton tai benh nao voi ma tren');
end;
/
-------------------------------------------

-- 11
create or replace procedure proc_benhnhan_laybenhnhan (p_result OUT SYS_REFCURSOR)
as
begin
    OPEN p_result FOR SELECT * FROM BENHNHAN ORDER BY MABN ASC;
end;
/
-- 12
create or replace procedure proc_benhnhan_them1benhnhan (
                                                    BNMaBN BENHNHAN.MABN%TYPE,
                                                    BNHoTen BENHNHAN.HOTEN%TYPE,
                                                    BNGioiTinh BENHNHAN.GIOITINH%TYPE,
                                                    BNNgaySinh VARCHAR,
                                                    BNQueQuan BENHNHAN.QUEQUAN%TYPE,
                                                    BNNoiOHienTai BENHNHAN.NOIOHIENTAI%TYPE,
                                                    changedrows OUT INT
                                                    )
as
begin
    INSERT INTO BENHNHAN VALUES (BNMaBN, BNHoTen, BNGioiTinh, TO_DATE(BNNgaySinh,'DD/MM/YYYY'), BNQueQuan, BNNoiOHienTai, 1);
    changedrows := SQL%ROWCOUNT;
    commit;
end;
/
-- 13
create or replace procedure proc_benhnhan_xoa1benhnhan (
                                                    BNMaBN BENHNHAN.MABN%TYPE,
                                                    changedrows OUT INT
                                                    )
as
begin
    DELETE FROM BENHNHAN 
        WHERE MABN = BNMaBN;
    changedrows := SQL%ROWCOUNT;
    commit;
end;
/
-- 14
create or replace procedure proc_benhnhan_sua1benhnhan (
                                                    BNMaBN BENHNHAN.MABN%TYPE,
                                                    BNHoTen BENHNHAN.HOTEN%TYPE,
                                                    BNGioiTinh BENHNHAN.GIOITINH%TYPE,
                                                    BNNgaySinh VARCHAR,
                                                    BNQueQuan BENHNHAN.QUEQUAN%TYPE,
                                                    BNNoiOHienTai BENHNHAN.NOIOHIENTAI%TYPE,
                                                    BNKhaNangDatLich BENHNHAN.KHANANGDATLICH%TYPE,
                                                    changedrows OUT INT
                                                    )
as
    v_mabn BENHNHAN.MABN%TYPE;
begin
    SELECT MABN INTO v_mabn FROM BENHNHAN WHERE MABN = BNMaBN;
    UPDATE BENHNHAN 
        SET HOTEN = BNHoTen, GIOITINH = BNGioiTinh, NGAYSINH = TO_DATE(BNNgaySinh,'DD/MM/YYYY'), QUEQUAN = BNQueQuan, NOIOHIENTAI = BNNoiOHienTai, KHANANGDATLICH = BNKhaNangDatLich
        WHERE MABN = BNMaBN;
    changedrows := SQL%ROWCOUNT;
    commit;    
exception
    when no_data_found then
        raise_application_error(-20136, 'Khong ton tai benh nhan nao voi ma tren');
end;
/
-----------------------------------------

-- 15
create or replace procedure proc_cabenh_laycabenh (p_result OUT SYS_REFCURSOR)
as
begin
    OPEN p_result FOR SELECT * FROM CABENH ORDER BY MACA ASC;
end;
/
-- 16
create or replace procedure proc_cabenh_them1cabenh (
                                                    CBMaCa CABENH.MACA%TYPE,
                                                    CBMaBN CABENH.MABN%TYPE,
                                                    CBMaBS CABENH.MABS%TYPE,
                                                    CBMaBenh CABENH.MABENH%TYPE,
                                                    CBMucDo CABENH.MUCDO%TYPE,
                                                    CBHinhThuc CABENH.HINHTHUC%TYPE,
                                                    CBBatDau VARCHAR,
                                                    CBKetThuc VARCHAR,
                                                    CBTinhTrang CABENH.TINHTRANG%TYPE,
                                                    CBMaPhong CABENH.MAPHONG%TYPE,
                                                    changedrows OUT INT
                                                    )
as
    v_controng PHONGBENH.CONTRONG%TYPE;
    khongcontrong EXCEPTION;
begin
    IF CBMaPhong IS NOT NULL THEN
        SELECT CONTRONG INTO v_controng FROM PHONGBENH WHERE MAPHONG = CBMaPhong;
        IF v_controng <= 0 THEN
            RAISE khongcontrong;
        END IF;
    END IF;
   
    INSERT INTO CABENH VALUES (CBMaCa, CBMaBN, CBMaBS, CBMaBenh, CBMucDo, CBHinhThuc, TO_TIMESTAMP(CBBatDau,'DD/MM/YYYY HH24:MI:SS'), TO_TIMESTAMP(CBKetThuc,'DD/MM/YYYY HH24:MI:SS'), CBTinhTrang, CBMaPhong, TO_TIMESTAMP(CBBatDau,'DD/MM/YYYY HH24:MI:SS'));
    
    changedrows := SQL%ROWCOUNT;
    UPDATE CABENH SET MAPHONG = CBMaPhong WHERE MABN = CBMaBN;
    
    UPDATE PHONGBENH SET CONTRONG = SUCCHUA - func_phongbenh_tinhsisophong(MAPHONG);
    commit;
exception
    when khongcontrong then
        raise_application_error('-20105','Phong da day');
end;
/
-- 17
create or replace procedure proc_cabenh_xoa1cabenh (
                                                    CBMaCa CABENH.MACA%TYPE,
                                                    changedrows OUT INT
                                                    )
as
    v_maphongtruoc PHONGBENH.MAPHONG%TYPE;
begin
    SELECT MAPHONG INTO v_maphongtruoc FROM CABENH WHERE MACA = CBMaCa;
    DELETE FROM CABENH 
        WHERE MACA = CBMaCa;
    
    changedrows := SQL%ROWCOUNT;
    IF v_maphongtruoc IS NOT NULL THEN
       UPDATE PHONGBENH SET CONTRONG = SUCCHUA - func_phongbenh_tinhsisophong(v_maphongtruoc);
    END IF;
    commit;
end;
/
-- 18
create or replace procedure proc_cabenh_sua1cabenh (
                                                    CBMaCa CABENH.MACA%TYPE,
                                                    CBMucDo CABENH.MUCDO%TYPE,
                                                    CBHinhThuc CABENH.HINHTHUC%TYPE,
                                                    CBKetThuc VARCHAR,
                                                    CBTinhTrang CABENH.TINHTRANG%TYPE,
                                                    CBMaPhong CABENH.MAPHONG%TYPE,
                                                    changedrows OUT INT
                                                    )
as
    v_maphongtruoc PHONGBENH.MAPHONG%TYPE;
    khongcontrong EXCEPTION;
    v_controng PHONGBENH.CONTRONG%TYPE := 1;
    v_mabn CABENH.MABN%TYPE;
    v_tinhtrangtruoc CABENH.TINHTRANG%TYPE;
    cadaketthuc EXCEPTION;
    v_maca CABENH.MACA%TYPE;
    ngaydaketthuc EXCEPTION;
    v_ngaychuyengannhat CABENH.NGAYCHUYENGANNHAT%TYPE;
    giathuephong PHONGBENH.GIA1NGAY%TYPE;
    v_mahd HOADONVIENPHI.MAHD%TYPE;
begin
    -- Kiem tra su ton tai cua ca benh --
    SELECT MACA INTO v_maca FROM CABENH WHERE MACA = CBMaCa;
    
    -- Kiem tra tinh trang ca benh truoc khi thay doi --
    SELECT TINHTRANG INTO v_tinhtrangtruoc FROM CABENH WHERE MACA = CBMaCa;
    IF v_tinhtrangtruoc = 'Da ket thuc' THEN
        RAISE cadaketthuc;
    END IF;
 
    -- Tinh hoa don khi vua ket thuc --
    SELECT MAHD INTO v_mahd FROM HOADONVIENPHI WHERE MACA = CBMaCa;
    IF v_tinhtrangtruoc <> 'Da ket thuc' AND CBTinhTrang = 'Da ket thuc' THEN
        proc_hoadonvienphi_tinhhoadon(v_mahd, CBMaCa);
    END IF;
    
     -- Kiem tra ngay ket thuc khong nho hon ngay hien tai --
    IF(TO_TIMESTAMP(CBKetThuc,'DD/MM/YYYY HH24:MI:SS') < CURRENT_TIMESTAMP) THEN
        raise ngaydaketthuc;
    END IF;
    
    -- Kiem tra thong tin phong co con trong khong --
    SELECT MAPHONG INTO v_maphongtruoc FROM CABENH WHERE MACA = CBMaCa;
    IF CBMaPhong is not null THEN
        SELECT CONTRONG INTO v_controng FROM PHONGBENH WHERE MAPHONG = CBMaPhong;
    END IF;
    IF (v_maphongtruoc <> CBMaPhong AND CBMaPhong is not null AND ((v_controng-1) < 0)) THEN
        RAISE khongcontrong;
    END IF;

    -- Kiem tra chuyen phong va tinh tien thue --
    SELECT NGAYCHUYENGANNHAT INTO v_ngaychuyengannhat FROM CABENH WHERE MACA = CBMaCa;
    IF (v_maphongtruoc is not null and v_maphongtruoc <> CBMaPhong) THEN
        SELECT GIA1NGAY INTO giathuephong FROM PHONGBENH WHERE MAPHONG = v_maphongtruoc;
        UPDATE HOADONVIENPHI
            SET TIENKHAM = TIENKHAM + (giathuephong * (TRUNC(CURRENT_TIMESTAMP) - TRUNC(v_ngaychuyengannhat)))
        WHERE MACA = CBMaCa;
        v_ngaychuyengannhat := CURRENT_TIMESTAMP;
    END IF;
    
    -- Cap nhat thong tin ca benh --
    UPDATE CABENH 
        SET MUCDO = CBMucDo, HINHTHUC = CBHinhThuc, NGAYKETTHUC = TO_TIMESTAMP(CBKetThuc,'DD/MM/YYYY HH24:MI:SS'), TINHTRANG = CBTinhTrang, MAPHONG = CBMaPhong, NGAYCHUYENGANNHAT = v_ngaychuyengannhat
        WHERE MACA = CBMaCa;
    changedrows := SQL%ROWCOUNT;
    
    -- Cap nhat thong tin phong moi cho nhung ca co cung benh nhan --
    SELECT MABN INTO v_mabn FROM CABENH WHERE MACA = CBMaCa;
    UPDATE CABENH
        SET MAPHONG = CBMaPhong WHERE MABN = v_mabn AND TINHTRANG != 'Da ket thuc';
    
    -- Cap nhat thong tin so luong con trong cho nhung phong moi va phong cu --
    IF v_maphongtruoc IS NOT NULL THEN
        UPDATE PHONGBENH SET CONTRONG = SUCCHUA - func_phongbenh_tinhsisophong(v_maphongtruoc) WHERE MAPHONG = v_maphongtruoc;
    END IF;
    IF CBMaPhong IS NOT NULL THEN
        UPDATE PHONGBENH SET CONTRONG = SUCCHUA - func_phongbenh_tinhsisophong(CBMaPhong) WHERE MAPHONG = CBMaPhong;
    END IF;
    
    -- Cap nhat so luong thiet bi --
    UPDATE THIETBIYTE SET SLCONLAI = SLTONG - func_thietbiyte_tinhsothietbidieuphoi(MATHIETBI) WHERE LOAISD = 'Tai su dung';
    commit;
exception
    when no_data_found then
        raise_application_error (-20133, 'Khong ton tai ca benh nao voi ma tren');
    when cadaketthuc then
        raise_application_error(-20131,'Ca da ket thuc. Khong the chinh sua');
    when ngaydaketthuc then
        raise_application_error (-20143, 'Ngay ket thuc nho hon ngay hien tai. Ca benh khong the tiep tuc. Vui long keo dai thoi diem ket thuc');
    when khongcontrong then
        raise_application_error (-20106, 'Phong da day');
end;
/
-----------------------------------------

-- 19
create or replace procedure proc_dieuphoithietbi_laydieuphoithietbi (p_result OUT SYS_REFCURSOR)
as
begin
    OPEN p_result FOR SELECT * FROM DIEUPHOITHIETBI ORDER BY MACA, MATHIETBI ASC;
end;
/
-- 20
create or replace procedure proc_dieuphoithietbi_them1dieuphoithietbi (
                                                    DPMaCa DIEUPHOITHIETBI.MACA%TYPE,
                                                    DPMaThietBi DIEUPHOITHIETBI.MATHIETBI%TYPE,
                                                    DPSoLuong DIEUPHOITHIETBI.SOLUONG%TYPE,
                                                    DPDieuPhoi VARCHAR,
                                                    DPKetThuc VARCHAR,
                                                    changedrows OUT INT
                                                    )
as
    v_tinhtrang CABENH.TINHTRANG%TYPE;
    tinhtrangkhonghople EXCEPTION;
     v_slconlai THIETBIYTE.SLCONLAI%TYPE;
    khongdusoluong EXCEPTION;
   
begin
    SELECT TINHTRANG INTO v_tinhtrang FROM CABENH WHERE MACA = DPMaCa;
    IF v_tinhtrang = 'Da ket thuc' THEN
        RAISE tinhtrangkhonghople;
    END IF;
    
    SELECT SLCONLAI into v_slconlai FROM THIETBIYTE WHERE MATHIETBI = DPMaThietBi;
    IF ((v_slconlai - DPSoLuong) < 0 OR DPSoLuong < 0) THEN
        raise khongdusoluong;
    END IF;
    
    INSERT INTO DIEUPHOITHIETBI VALUES (DPMaCa, DPMaThietBi, DPSoLuong, TO_TIMESTAMP(DPDieuPhoi,'DD/MM/YYYY HH24:MI:SS'), TO_TIMESTAMP(DPKetThuc,'DD/MM/YYYY HH24:MI:SS'));
    changedrows := SQL%ROWCOUNT;
    UPDATE THIETBIYTE SET SLCONLAI = SLCONLAI - DPSoLuong WHERE MATHIETBI = DPMaThietBi;
    UPDATE THIETBIYTE SET SLTONG = SLCONLAI WHERE MATHIETBI = DPMaThietBi AND LOAISD = '1 lan';
    commit;
exception
    when tinhtrangkhonghople then
        raise_application_error(-20108,'Ca benh da ket thuc. Khong the dieu phoi');
    when khongdusoluong then
        raise_application_error(-20109,'Thiet bi khong du so luong');
end;
/
-- 21
create or replace procedure proc_dieuphoithietbi_xoa1dieuphoithietbi (
                                                    DPMaCa DIEUPHOITHIETBI.MACA%TYPE,
                                                    DPMaThietBi DIEUPHOITHIETBI.MATHIETBI%TYPE,
                                                    DPDieuPhoi VARCHAR,
                                                    changedrows OUT INT
                                                    )
as
    v_soluong DIEUPHOITHIETBI.SOLUONG%TYPE := 0;
begin
    SELECT SOLUONG INTO v_soluong FROM DIEUPHOITHIETBI
        WHERE MACA = DPMaCa AND MATHIETBI = DPMaThietBi AND NGAYDIEUPHOI = TO_TIMESTAMP (DPDieuPhoi , 'DD/MM/YYYY HH24:MI:SS');
    DELETE FROM DIEUPHOITHIETBI
        WHERE MACA = DPMaCa AND MATHIETBI = DPMaThietBi AND NGAYDIEUPHOI = TO_TIMESTAMP (DPDieuPhoi , 'DD/MM/YYYY HH24:MI:SS');
    changedrows := SQL%ROWCOUNT;
    UPDATE THIETBIYTE SET SLCONLAI = SLCONLAI + v_soluong WHERE MATHIETBI = DPMaThietBi AND LOAISD = 'Tai su dung';
    commit;
end;
/
-- 22
create or replace procedure proc_dieuphoithietbi_sua1dieuphoithietbi (
                                                                    DPMaCa DIEUPHOITHIETBI.MACA%TYPE,
                                                                    DPMaThietBi DIEUPHOITHIETBI.MATHIETBI%TYPE,
                                                                    DPSoLuong DIEUPHOITHIETBI.SOLUONG%TYPE,
                                                                    DPDieuPhoi VARCHAR,
                                                                    DPKetThuc VARCHAR,
                                                                    changedrows OUT INT
                                                                    )
as
    v_slconlai THIETBIYTE.SLCONLAI%TYPE;
    khongdusoluong EXCEPTION;
    v_sltruoc DIEUPHOITHIETBI.SOLUONG%TYPE := 0;
    v_maca DIEUPHOITHIETBI.MACA%TYPE;
begin
    SELECT MACA INTO v_maca FROM DIEUPHOITHIETBI
        WHERE MACA = DPMaCa AND MATHIETBI = DPMaThietbi AND NGAYDIEUPHOI = TO_TIMESTAMP (DPDieuPhoi , 'DD/MM/YYYY HH24:MI:SS');
    SELECT SLCONLAI into v_slconlai FROM THIETBIYTE 
        WHERE MATHIETBI = DPMaThietBi;
    SELECT SOLUONG INTO v_sltruoc FROM DIEUPHOITHIETBI 
        WHERE MACA = DPMaCa AND MATHIETBI = DPMaThietbi AND NGAYDIEUPHOI = TO_TIMESTAMP (DPDieuPhoi , 'DD/MM/YYYY HH24:MI:SS');
    IF ((v_slconlai - DPSoLuong + v_sltruoc) < 0 OR DPSoLuong < 0) THEN
        raise khongdusoluong;
    END IF;
    
    UPDATE DIEUPHOITHIETBI 
        SET SOLUONG = DPSoLuong, NGAYKETTHUC = TO_TIMESTAMP(DPKetThuc,'DD/MM/YYYY HH24:MI:SS')
        WHERE MACA = DPMaCa AND MATHIETBI = DPMaThietBi AND NGAYDIEUPHOI = TO_TIMESTAMP (DPDieuPhoi , 'DD/MM/YYYY HH24:MI:SS');
    changedrows := SQL%ROWCOUNT;
    UPDATE THIETBIYTE SET SLCONLAI = SLCONLAI - DPSoLuong + v_sltruoc WHERE MATHIETBI = DPMaThietBi;
    UPDATE THIETBIYTE SET SLTONG = SLCONLAI WHERE MATHIETBI = DPMaThietBi AND LOAISD = '1 lan';
    commit;
exception
    when no_data_found then
        raise_application_error(-20137, 'Khong ton tai dieu phoi nao voi ma ca, ma thiet bi, thoi diem tren');
    when khongdusoluong then
        raise_application_error(-20110,'Thiet bi khong du so luong');
end;
/
-----------------------------------------

-- 23
create or replace procedure proc_phongbenh_layphongbenh (p_result OUT SYS_REFCURSOR)
as
begin
    --UPDATE PHONGBENH SET CONTRONG = SUCCHUA - func_phongbenh_tinhsisophong(MAPHONG);
    OPEN p_result FOR SELECT * FROM PHONGBENH ORDER BY MAPHONG ASC;
end;
/
-- 24
create or replace procedure proc_phongbenh_them1phongbenh (
                                                    PHMaPhong PHONGBENH.MAPHONG%TYPE,
                                                    PHLoai PHONGBENH.LOAI%TYPE,
                                                    PHToa PHONGBENH.TOA%TYPE,
                                                    PHLau PHONGBENH.LAU%TYPE,
                                                    PHSucChua PHONGBENH.SUCCHUA%TYPE,
                                                    PHGia1Ngay PHONGBENH.GIA1NGAY%TYPE,
                                                    changedrows OUT INT
                                                    )
as
     succhuakhongdu EXCEPTION;
begin
     IF PHSucChua < 0 THEN
        RAISE succhuakhongdu;
    END IF;
    INSERT INTO PHONGBENH VALUES (PHMaPhong, PHLoai, PHToa, PHLau, PHSucChua, PHSucChua - func_phongbenh_tinhsisophong(PHMaPhong), PHGia1Ngay);
    changedrows := SQL%ROWCOUNT;
    commit;
exception
    when succhuakhongdu then
        raise_application_error(-20130,'Phong co suc chua khong hop le');
end;
/
-- 25
create or replace procedure proc_phongbenh_xoa1phongbenh (
                                                    PHMaPhong PHONGBENH.MAPHONG%TYPE,
                                                    changedrows OUT INT
                                                    )
as
begin
    DELETE FROM PHONGBENH 
        WHERE MAPHONG = PHMaPhong;
    commit;
    changedrows := SQL%ROWCOUNT;
end;
/
-- 26
create or replace procedure proc_phongbenh_sua1phongbenh (
                                                    PHMaPhong PHONGBENH.MAPHONG%TYPE,
                                                    PHToa PHONGBENH.TOA%TYPE,
                                                    PHLau PHONGBENH.LAU%TYPE,
                                                    PHSucChua PHONGBENH.SUCCHUA%TYPE,
                                                    PHLoai PHONGBENH.LOAI%TYPE,
                                                    PHGia1Ngay PHONGBENH.GIA1NGAY%TYPE,
                                                    changedrows OUT INT
                                                    )
as
    succhuakhongdu EXCEPTION;
    v_maphong PHONGBENH.MAPHONG%TYPE;
begin
    SELECT MAPHONG INTO v_maphong FROM PHONGBENH WHERE MAPHONG = PHMaPhong;
    IF ((PHSucChua - func_phongbenh_tinhsisophong(PHMaPhong)) < 0 OR PHSucChua < 0) THEN
        RAISE succhuakhongdu;
    END IF;
    
    UPDATE PHONGBENH 
        SET TOA = PHToa, LAU = PHLau, SUCCHUA = PHSucChua, LOAI = PHLoai, CONTRONG = PHSucChua - (func_phongbenh_tinhsisophong(PHMaPhong)), GIA1NGAY = PHGia1Ngay
        WHERE MAPHONG = PHMaPhong;
   commit;
    changedrows := SQL%ROWCOUNT;

exception
    when no_data_found then
        raise_application_error(-20139, 'Khong ton tai phong benh nao voi ma tren');
    when succhuakhongdu then
        raise_application_error(-20112,'Phong khong chua du benh nhan hien co');
end;
/
-------------------------------------------------
-- 27
create or replace procedure proc_taikhoan_laytaikhoan (p_result OUT SYS_REFCURSOR)
as
begin
    OPEN p_result FOR SELECT * FROM TAIKHOAN ORDER BY TENDANGNHAP ASC;
end;
/
-- 28
create or replace procedure proc_taikhoan_them1taikhoan (
                                                    TKTenDangNhap TAIKHOAN.TENDANGNHAP%TYPE,
                                                    TKMatKhau TAIKHOAN.MATKHAU%TYPE,
                                                    changedrows OUT INT
                                                    )
as
begin
    INSERT INTO TAIKHOAN VALUES (TKTenDangNhap, TKMatKhau);
    changedrows := SQL%ROWCOUNT;
    commit;
end;
/
-- 29
create or replace procedure proc_taikhoan_xoa1taikhoan (
                                                    TKTenDangNhap TAIKHOAN.TENDANGNHAP%TYPE,
                                                    changedrows OUT INT
                                                    )
as
begin
    DELETE FROM TAIKHOAN 
        WHERE TENDANGNHAP = TKTenDangNhap;
    changedrows := SQL%ROWCOUNT;
    commit;
end;
/
-- 30
create or replace procedure proc_taikhoan_sua1taikhoan (
                                                    TKTenDangNhap TAIKHOAN.TENDANGNHAP%TYPE,
                                                    TKMatKhau TAIKHOAN.MATKHAU%TYPE,
                                                    changedrows OUT INT
                                                    )
as
    v_tendangnhap TAIKHOAN.TENDANGNHAP%TYPE;
begin
    SELECT TENDANGNHAP INTO v_tendangnhap FROM TAIKHOAN WHERE TENDANGNHAP = TKTenDangNhap;
    UPDATE TAIKHOAN 
        SET MATKHAU = TKMatKhau
        WHERE TENDANGNHAP = TKTenDangNhap;
    changedrows := SQL%ROWCOUNT;
    commit;
exception
    when no_data_found then
        raise_application_error(-20138, 'Khong ton tai tai khoan nao voi ten dang nhap tren');
end;
/
-----------------------------------------

-- 31
create or replace procedure proc_thietbiyte_laythietbiyte (p_result OUT SYS_REFCURSOR)
as
begin
   UPDATE THIETBIYTE SET SLCONLAI = SLTONG - func_thietbiyte_tinhsothietbidieuphoi(MATHIETBI) WHERE LOAISD = 'Tai su dung';
    UPDATE THIETBIYTE SET SLCONLAI = SLTONG WHERE LOAISD = '1 lan';
    commit;
    OPEN p_result FOR SELECT * FROM THIETBIYTE ORDER BY MATHIETBI ASC;
end;
/
-- 32
create or replace procedure proc_thietbiyte_them1thietbiyte (
                                                    TBMaThietBi THIETBIYTE.MATHIETBI%TYPE,
                                                    TBTenThietBi THIETBIYTE.TENTHIETBI%TYPE,
                                                    TBLoaiSD THIETBIYTE.LOAISD%TYPE,
                                                    TBCongDung THIETBIYTE.CONGDUNG%TYPE,
                                                    TBSLTong THIETBIYTE.SLTONG%TYPE,
                                                    TBGia THIETBIYTE.GIA%TYPE,
                                                    changedrows OUT INT
                                                    )
as
    khongdusoluong EXCEPTION;
begin
    IF TBSLTong < 0  THEN
        RAISE khongdusoluong;
    END IF;
    IF TBLoaiSD = 'Tai su dung' THEN
        INSERT INTO THIETBIYTE VALUES (TBMaThietBi, TBTenThietBi, TBLoaiSD, TBCongDung, TBSLTong, TBSLTong - func_thietbiyte_tinhsothietbidieuphoi(TBMaThietBi), TBGia);
        changedrows := SQL%ROWCOUNT;
    ELSE
        INSERT INTO THIETBIYTE VALUES (TBMaThietBi, TBTenThietBi, TBLoaiSD, TBCongDung, TBSLTong, TBSLTong, TBGia);
        changedrows := SQL%ROWCOUNT;
    END IF;
    commit;
exception    
    when khongdusoluong then
        raise_application_error (-20129,'Thiet bi khong du so luong');
end;
/
-- 33
create or replace procedure proc_thietbiyte_xoa1thietbiyte (
                                                    TBMaThietBi THIETBIYTE.MATHIETBI%TYPE,
                                                    changedrows OUT INT
                                                    )
as
begin
    DELETE FROM THIETBIYTE 
        WHERE MATHIETBI = TBMaThietBi;
    changedrows := SQL%ROWCOUNT;
    commit;
end;
/
-- 34
create or replace procedure proc_thietbiyte_sua1thietbiyte (
                                                    TBMaThietBi THIETBIYTE.MATHIETBI%TYPE,
                                                    TBTenThietBi THIETBIYTE.TENTHIETBI%TYPE,
                                                    TBLoaiSD THIETBIYTE.LOAISD%TYPE,
                                                    TBCongDung THIETBIYTE.CONGDUNG%TYPE,
                                                    TBSLTong THIETBIYTE.SLTONG%TYPE,
                                                    TBGia THIETBIYTE.GIA%TYPE,
                                                    changedrows OUT INT
                                                    )
as
    khongdudieuphoi EXCEPTION;
    v_mathietbi THIETBIYTE.MATHIETBI%TYPE;
begin
    SELECT MATHIETBI INTO v_mathietbi FROM THIETBIYTE WHERE MATHIETBI = TBMaThietBi;
    
    IF (((TBSLTong - func_thietbiyte_tinhsothietbidieuphoi(TBMaThietBi)) < 0 AND TBLoaiSD = 'Tai su dung') OR TBSLTong < 0)  THEN
        RAISE khongdudieuphoi;
    END IF;
    
    UPDATE THIETBIYTE 
        SET TENTHIETBI = TBTenThietBi, CONGDUNG = TBCongDung, SLTONG = TBSLTong, SLCONLAI = TBSLTong - func_thietbiyte_tinhsothietbidieuphoi(TBMaThietBi), GIA = TBGia
        WHERE MATHIETBI = TBMaThietBi;
    changedrows := SQL%ROWCOUNT;
     UPDATE THIETBIYTE SET SLCONLAI = SLCONLAI + func_thietbiyte_tinhsothietbidieuphoi(TBMaThietBi) WHERE MATHIETBI = TBMaThietBi AND LOAISD = '1 lan';
    commit;
exception
    when no_data_found then
        raise_application_error(-20140, 'Khong ton tai thiet bi y te nao voi ma tren');
    when khongdudieuphoi then
        raise_application_error (-20115,'Thiet bi khong du so luong cho luong dieu phoi hien co');
end;
/
------------------------------------------------

-- 35
create or replace procedure proc_cabenh_laycabenh_theobacsi (BSMaBS BACSI.MABS%TYPE, p_result OUT SYS_REFCURSOR)
as
begin
    OPEN p_result FOR SELECT MACA, MABN, MABENH, MUCDO, HINHTHUC, NGAYBATDAU, NGAYKETTHUC, TINHTRANG, MAPHONG FROM CABENH
                        WHERE MABS = BSMaBS ORDER BY MACA ASC;
end;
/

-- 36
create or replace procedure proc_cabenh_sua1cabenh_theobacsi (
                                                    BSMaBS BACSI.MABS%TYPE,
                                                    CBMaCa CABENH.MACA%TYPE,
                                                    CBMucDo CABENH.MUCDO%TYPE,
                                                    CBHinhThuc CABENH.HINHTHUC%TYPE,
                                                    CBKetThuc VARCHAR,
                                                    CBTinhTrang VARCHAR,
                                                    CBMaPhong CABENH.MAPHONG%TYPE,
                                                    changedrows OUT INT
                                                    )
as
    v_maphongtruoc CABENH.MAPHONG%TYPE;
    khongcontrong EXCEPTION;
    v_controng PHONGBENH.CONTRONG%TYPE;
    v_mabn CABENH.MABN%TYPE;
    v_tinhtrangtruoc CABENH.TINHTRANG%TYPE;
    cadaketthuc EXCEPTION;
    v_maca CABENH.MACA%TYPE;
    ngaydaketthuc EXCEPTION;
    v_ngaychuyengannhat CABENH.NGAYCHUYENGANNHAT%TYPE;
    giathuephong PHONGBENH.GIA1NGAY%TYPE;
    v_mahd HOADONVIENPHI.MAHD%TYPE;
begin
    SELECT MACA INTO v_maca FROM CABENH WHERE MACA = CBMaCa AND MABS = BSMaBS;

    SELECT TINHTRANG INTO v_tinhtrangtruoc FROM CABENH WHERE MACA = CBMaCa;
    IF v_tinhtrangtruoc = 'Da ket thuc' THEN
        RAISE cadaketthuc;
    END IF;
    
        -- Tinh hoa don khi vua ket thuc --
    SELECT MAHD INTO v_mahd FROM HOADONVIENPHI WHERE MACA = CBMaCa AND ROWNUM = 1;
    IF v_tinhtrangtruoc <> 'Da ket thuc' AND CBTinhTrang = 'Da ket thuc' THEN
        proc_hoadonvienphi_tinhhoadon(v_mahd, CBMaCa);
    END IF;
    
    IF(TO_TIMESTAMP(CBKetThuc,'DD/MM/YYYY HH24:MI:SS') < CURRENT_TIMESTAMP) THEN
        raise ngaydaketthuc;
    END IF;
    
    SELECT MAPHONG INTO v_maphongtruoc FROM CABENH WHERE MACA = CBMaCa;
    IF CBMaPhong is not null THEN
        SELECT CONTRONG INTO v_controng FROM PHONGBENH WHERE MAPHONG = CBMaPhong;
    END IF;
    SELECT MABN INTO v_mabn FROM CABENH WHERE MACA = CBMaCa;
    IF (v_maphongtruoc <> CBMaPhong AND CBMaPhong is not null AND ((v_controng-1) < 0)) THEN
        RAISE khongcontrong;
    END IF;

        -- Kiem tra chuyen phong va tinh tien thue --
    SELECT NGAYCHUYENGANNHAT INTO v_ngaychuyengannhat FROM CABENH WHERE MACA = CBMaCa;
    IF (v_maphongtruoc is not null and v_maphongtruoc <> CBMaPhong) THEN
        SELECT GIA1NGAY INTO giathuephong FROM PHONGBENH WHERE MAPHONG = v_maphongtruoc;
        UPDATE HOADONVIENPHI
            SET TIENKHAM = TIENKHAM + (giathuephong * (TRUNC(CURRENT_TIMESTAMP) - TRUNC(v_ngaychuyengannhat)))
        WHERE MACA = CBMaCa;
        v_ngaychuyengannhat := CURRENT_TIMESTAMP;
    END IF;

    UPDATE CABENH 
        SET MUCDO = CBMucDo, HINHTHUC = CBHinhThuc, NGAYKETTHUC = TO_TIMESTAMP(CBKetThuc,'DD/MM/YYYY HH24:MI:SS'), TINHTRANG = CBTinhTrang, MAPHONG = CBMaPhong, NGAYCHUYENGANNHAT = v_ngaychuyengannhat
        WHERE MACA = CBMaCa AND MABS = BSMaBS;
    changedrows := SQL%ROWCOUNT;
    
    UPDATE CABENH
        SET MAPHONG = CBMaPhong WHERE MABN = v_mabn AND TINHTRANG != 'Da ket thuc';
    
    IF v_maphongtruoc IS NOT NULL THEN
        UPDATE PHONGBENH SET CONTRONG = SUCCHUA - func_phongbenh_tinhsisophong(v_maphongtruoc) WHERE MAPHONG = v_maphongtruoc;
    END IF;
    IF CBMaPhong IS NOT NULL THEN
        UPDATE PHONGBENH SET CONTRONG = SUCCHUA - func_phongbenh_tinhsisophong(CBMaPhong) WHERE MAPHONG = CBMaPhong;
    END IF;

    UPDATE THIETBIYTE SET SLCONLAI = SLTONG - func_thietbiyte_tinhsothietbidieuphoi(MATHIETBI) WHERE LOAISD = 'Tai su dung';
    commit;
exception
    when no_data_found then
        raise_application_error(-20141, 'Khong ton tai ca benh nao voi ma tren ung voi ma bac si');
    when cadaketthuc then
        raise_application_error (-20131,'Ca da ket thuc');
    when ngaydaketthuc then
        raise_application_error (-20144, 'Ngay ket thuc nho hon ngay hien tai. Ca benh khong the tiep tuc. Vui long keo dai thoi diem ket thuc');
    when khongcontrong then
        raise_application_error (-20117, 'Phong da day');
end;
/

-- 37
create or replace procedure proc_dieuphoithietbi_laydieuphoithietbi_theobacsi (BSMaBS BACSI.MABS%TYPE, p_result OUT SYS_REFCURSOR)
as
begin
    OPEN p_result FOR SELECT DISTINCT DP.* FROM DIEUPHOITHIETBI DP, CABENH CB 
                        WHERE CB.MABS = BSMaBS AND CB.MACA = DP.MACA ORDER BY DP.MACA, DP.MATHIETBI ASC;
end;
/

-- 38
create or replace procedure proc_dieuphoithietbi_them1dieuphoithietbi_theobacsi (
                                                    BSMaBS BACSI.MABS%TYPE,
                                                    DPMaCa DIEUPHOITHIETBI.MACA%TYPE,
                                                    DPMaThietBi DIEUPHOITHIETBI.MATHIETBI%TYPE,
                                                    DPSoLuong DIEUPHOITHIETBI.SOLUONG%TYPE,
                                                    DPDieuPhoi VARCHAR,
                                                    DPKetThuc VARCHAR,
                                                    changedrows OUT INT
                                                    )
as
    v_tinhtrang CABENH.TINHTRANG%TYPE;
    tinhtrangkhonghople EXCEPTION;
    soca INT := 0;
    khongcoca EXCEPTION;
    v_slconlai THIETBIYTE.SLCONLAI%TYPE;
    khongdusoluong EXCEPTION;
begin
    
    SELECT COUNT(*) INTO soca FROM CABENH WHERE MABS = BSMaBS AND MACA = DPMaCa;
    IF soca = 0 THEN
        RAISE khongcoca;
    END IF;
    
    SELECT TINHTRANG INTO v_tinhtrang FROM CABENH WHERE MACA = DPMaCa;
    IF v_tinhtrang = 'Da ket thuc' THEN
        RAISE tinhtrangkhonghople;
    END IF;
    
    SELECT SLCONLAI into v_slconlai FROM THIETBIYTE WHERE MATHIETBI = DPMaThietBi;
    IF ((v_slconlai - DPSoLuong) < 0 OR DPSoLuong < 0)THEN
        raise khongdusoluong;
    END IF;
    
    INSERT INTO DIEUPHOITHIETBI VALUES (DPMaCa, DPMaThietBi, DPSoLuong, TO_TIMESTAMP(DPDieuPhoi,'DD/MM/YYYY HH24:MI:SS'), TO_TIMESTAMP(DPKetThuc,'DD/MM/YYYY HH24:MI:SS'));
    changedrows := SQL%ROWCOUNT;
    UPDATE THIETBIYTE SET SLCONLAI = (SLCONLAI - DPSoLuong) WHERE MATHIETBI = DPMaThietBi;
    UPDATE THIETBIYTE SET SLTONG = SLCONLAI WHERE MATHIETBI = DPMaThietBi AND LOAISD = '1 lan';
    commit;
exception
    when tinhtrangkhonghople then
        raise_application_error(-20119,'Ca benh da ket thuc. Khong the dieu phoi');
    when khongcoca then
        raise_application_error(-20120, 'Khong duoc tao dieu phoi thiet bi ca benh cua bac si khac');
    when khongdusoluong then
        raise_application_error(-20121,'Thiet bi khong du so luong');
end;
/

-- 39
create or replace procedure proc_dieuphoithietbi_sua1dieuphoithietbi_theobacsi (
                                                    BSMaBS BACSI.MABS%TYPE,
                                                    DPMaCa DIEUPHOITHIETBI.MACA%TYPE,
                                                    DPMaThietBi DIEUPHOITHIETBI.MATHIETBI%TYPE,
                                                    DPSoLuong DIEUPHOITHIETBI.SOLUONG%TYPE,
                                                    DPDieuPhoi VARCHAR,
                                                    DPKetThuc VARCHAR,
                                                    changedrows OUT INT
                                                    )
as
     soca INT := 0;
    khongcoca EXCEPTION;
     v_slconlai THIETBIYTE.SLCONLAI%TYPE;
    khongdusoluong EXCEPTION;
    v_sltruoc DIEUPHOITHIETBI.SOLUONG%TYPE := 0;
    v_maca CABENH.MACA%TYPE;
begin
    SELECT COUNT(*) INTO soca FROM CABENH WHERE MABS = BSMaBS AND MACA = DPMaCa;
    IF soca = 0 THEN
        RAISE khongcoca;
    END IF;
    
    SELECT MACA, SOLUONG INTO v_maca, v_sltruoc FROM DIEUPHOITHIETBI 
        WHERE MACA = DPMaCa AND MATHIETBI = DPMaThietbi AND NGAYDIEUPHOI = TO_TIMESTAMP (DPDieuPhoi , 'DD/MM/YYYY HH24:MI:SS');
    SELECT SLCONLAI INTO v_slconlai FROM THIETBIYTE 
        WHERE MATHIETBI = DPMaThietBi;
    IF ((v_slconlai - DPSoLuong + v_sltruoc) < 0 OR DPSoLuong < 0) THEN
        raise khongdusoluong;
    END IF;
    
    UPDATE DIEUPHOITHIETBI 
        SET SOLUONG = DPSoLuong, NGAYKETTHUC = TO_TIMESTAMP(DPKetThuc,'DD/MM/YYYY HH24:MI:SS')
        WHERE MACA = DPMaCa AND MATHIETBI = DPMaThietBi AND NGAYDIEUPHOI = TO_TIMESTAMP (DPDieuPhoi , 'DD/MM/YYYY HH24:MI:SS');
    changedrows := SQL%ROWCOUNT;
    UPDATE THIETBIYTE SET SLCONLAI = SLCONLAI - DPSoLuong + v_sltruoc WHERE MATHIETBI = DPMaThietBi;
    UPDATE THIETBIYTE SET SLTONG = SLCONLAI WHERE MATHIETBI = DPMaThietBi AND LOAISD = '1 lan';
    commit;
exception
    when no_data_found then
        raise_application_error(-20142, 'Khong ton tai dieu phoi nao voi ma ca, ma thiet bi, thoi diem tren');
    when khongcoca then
        raise_application_error(-20122, 'Khong duoc sua dieu phoi thiet bi ca benh cua bac si khac');
    when khongdusoluong then
        raise_application_error(-20123,'Thiet bi khong du so luong');
end;
/
-- 40
create or replace procedure proc_bacsi_laybacsi_theobacsi (BSMaBS BACSI.MABS%TYPE, p_result OUT SYS_REFCURSOR)
as
begin
    OPEN p_result FOR SELECT * FROM BACSI WHERE MABS = BSMaBS ORDER BY MABS ASC;
end;
/
-------------------------------------------------
-- 41
create or replace procedure proc_bacsi_laybacsi_theobenhnhan (BNMaBN BENHNHAN.MABN%TYPE, p_result OUT SYS_REFCURSOR)
as
begin
    OPEN p_result FOR SELECT DISTINCT BS.* FROM BACSI BS, CABENH CB 
                        WHERE CB.MABN = BNMaBN AND BS.MABS = CB.MABS ORDER BY BS.MABS ASC;
end;
/
-- 42
create or replace procedure proc_cabenh_laycabenh_theobenhnhan (BNMaBN BENHNHAN.MABN%TYPE, p_result OUT SYS_REFCURSOR)
as
begin
    OPEN p_result FOR SELECT DISTINCT CB.MACA, CB.MABS, CB.MABENH, B.TENBENH, CB.MUCDO, CB.HINHTHUC, CB.NGAYBATDAU, CB.NGAYKETTHUC, CB.TINHTRANG, CB.MAPHONG
                        FROM CABENH CB, BENH B WHERE CB.MABN = BNMaBN AND CB.MABENH = B.MABENH ORDER BY CB.MACA ASC;
end;
/
-- 43
create or replace procedure proc_dieuphoithietbi_laydieuphoithietbi_theobenhnhan (BNMaBN BENHNHAN.MABN%TYPE, p_result OUT SYS_REFCURSOR)
as
begin
    OPEN p_result FOR SELECT DISTINCT DP.MATHIETBI, TB.TENTHIETBI, TB.CONGDUNG, DP.SOLUONG, DP.NGAYDIEUPHOI, DP.NGAYKETTHUC
                        FROM DIEUPHOITHIETBI DP, THIETBIYTE TB, CABENH CB 
                        WHERE DP.MACA = CB.MACA AND CB.MABN = BNMaBN AND DP.MATHIETBI = TB.MATHIETBI 
                        ORDER BY DP.MATHIETBI ASC;
end;
/
-- 44
create or replace procedure proc_benhnhan_laybenhnhan_theobenhnhan (BNMaBN BENHNHAN.MABN%TYPE, p_result OUT SYS_REFCURSOR)
as
begin
    OPEN p_result FOR SELECT * FROM BENHNHAN WHERE MABN = BNMaBN ORDER BY MABN ASC;
end;
/
-- 45
create or replace procedure proc_cabenh_laybacsi_theobenh (QLMaBenh BENH.MABENH%TYPE, p_result OUT SYS_REFCURSOR)
as
begin
    IF QLMaBenh IS NULL THEN
         OPEN p_result FOR SELECT * FROM BACSI ORDER BY MABS ASC;
    ELSE
        OPEN p_result FOR SELECT * FROM BACSI BS, BENH
        WHERE BENH.MABENH = QLMaBenh AND BENH.TENKHOA = BS.TENKHOA ORDER BY MABS ASC;
    END IF;
end;
/
-- 46
create or replace procedure proc_cabenh_laybenh_theobacsi (QLMaBS BACSI.MABS%TYPE, p_result OUT SYS_REFCURSOR)
as
begin
    IF QLMaBS IS NULL THEN
         OPEN p_result FOR SELECT * FROM BENH ORDER BY MABENH ASC;
    ELSE
        OPEN p_result FOR SELECT * FROM BENH, BACSI BS
        WHERE BS.MABS = QLMaBS AND BS.TENKHOA = BENH.TENKHOA ORDER BY MABENH ASC;
    END IF;
end;
/
----------------------------------------------
-- 47
create or replace procedure proc_thuoc_laythuoc (p_result OUT SYS_REFCURSOR)
as
begin
    OPEN p_result FOR SELECT * FROM THUOC ORDER BY MATHUOC ASC;
end;
/
-- 48
create or replace procedure proc_thuoc_them1thuoc (
                                                    THMaThuoc THUOC.MATHUOC%TYPE,
                                                    THTenThuoc THUOC.TENTHUOC%TYPE,
                                                    THCongDung THUOC.CONGDUNG%TYPE,
                                                    THSLConLai THUOC.SLCONLAI%TYPE,
                                                    THGia THUOC.GIA%TYPE,
                                                    changedrows OUT INT
                                                    )
as
begin
    INSERT INTO THUOC VALUES (THMaThuoc, THTenThuoc, THCongDung, THSLConLai, THGia);
    changedrows := SQL%ROWCOUNT;
    commit;
end;
/
-- 49
create or replace procedure proc_thuoc_xoa1thuoc (
                                                    THMaThuoc THUOC.MATHUOC%TYPE,
                                                    changedrows OUT INT
                                                    )
as
begin
    DELETE FROM THUOC 
        WHERE MATHUOC = THMaThuoc;
    changedrows := SQL%ROWCOUNT;
    commit;
end;
/
-- 50
create or replace procedure proc_thuoc_sua1thuoc (
                                                    THMaThuoc THUOC.MATHUOC%TYPE,
                                                    THTenThuoc THUOC.TENTHUOC%TYPE,
                                                    THCongDung THUOC.CONGDUNG%TYPE,
                                                    THSLConLai THUOC.SLCONLAI%TYPE,
                                                    THGia THUOC.GIA%TYPE,
                                                    changedrows OUT INT
                                                    )
as
    v_mathuoc THUOC.MATHUOC%TYPE;
begin
    SELECT MATHUOC INTO v_mathuoc FROM THUOC WHERE MATHUOC = THMaThuoc;
    
    UPDATE THUOC 
        SET TENTHUOC = THTenThuoc, CONGDUNG = THCongDung, SLCONLAI = THSLConLai, GIA = THGia
        WHERE MATHUOC = THMaThuoc;
    changedrows := SQL%ROWCOUNT;
    commit;
exception
    when no_data_found then
        raise_application_error(-20145, 'Khong ton tai thuoc nao voi ma tren');
end;
/
----------------------------------------------
-- 51
create or replace procedure proc_kethuoc_laykethuoc (p_result OUT SYS_REFCURSOR)
as
begin
    OPEN p_result FOR SELECT * FROM KETHUOC ORDER BY NGAYKE DESC;
end;
/
-- 52
create or replace procedure proc_kethuoc_them1kethuoc (
                                                    KTMaCa KETHUOC.MACA%TYPE,
                                                    KTMaThuoc KETHUOC.MATHUOC%TYPE,
                                                    KTNgayKe varchar,
                                                    KTSL KETHUOC.SL%TYPE,
                                                    changedrows OUT INT
                                                    )
as
    sltoida THUOC.SLCONLAI%TYPE;
    khongdusl EXCEPTION;
begin
    SELECT SLCONLAI INTO sltoida FROM THUOC WHERE MATHUOC = KTMaThuoc;
    IF (KTSL > sltoida) THEN
        RAISE khongdusl;
    END IF;
    INSERT INTO KETHUOC VALUES (KTMaCa, KTMaThuoc, TO_TIMESTAMP(KTNgayKe,'DD/MM/YYYY HH24:MI:SS'), KTSL);
    changedrows := SQL%ROWCOUNT;
    
    UPDATE THUOC SET SLCONLAI = SLCONLAI - KTSL WHERE MATHUOC = KTMaThuoc;
    commit;
exception
    when khongdusl then
        raise_application_error (-20151,'Khong du so luong thuoc');
end;
/
-- 53
create or replace procedure proc_kethuoc_xoa1kethuoc (
                                                    KTMaCa KETHUOC.MACA%TYPE,
                                                    KTMaThuoc KETHUOC.MATHUOC%TYPE,
                                                    KTNgayKe KETHUOC.NGAYKE%TYPE,
                                                    changedrows OUT INT
                                                    )
as
begin
    DELETE FROM KETHUOC 
        WHERE MACA = KTMaCa AND MATHUOC = KTMaThuoc AND NGAYKE = TO_TIMESTAMP(KTNgayKe,'DD/MM/YYYY HH24:MI:SS');
    changedrows := SQL%ROWCOUNT;
    commit;
end;
/
-- 54
create or replace procedure proc_kethuoc_sua1kethuoc (
                                                    KTMaCa KETHUOC.MACA%TYPE,
                                                    KTMaThuoc KETHUOC.MATHUOC%TYPE,
                                                    KTNgayKe varchar,
                                                    KTSL KETHUOC.SL%TYPE,
                                                    changedrows OUT INT
                                                    )
as
    v_mathuoc KETHUOC.MATHUOC%TYPE;
begin
    SELECT MATHUOC INTO v_mathuoc FROM KETHUOC WHERE MACA = KTMaCa AND MATHUOC = KTMaThuoc AND NGAYKE = TO_TIMESTAMP(KTNgayKe,'DD/MM/YYYY HH24:MI:SS'); 
    
    UPDATE KETHUOC 
        SET SL = KTSL
        WHERE MACA = KTMaCa AND MATHUOC = KTMaThuoc AND NGAYKE = TO_TIMESTAMP(KTNgayKe,'DD/MM/YYYY HH24:MI:SS'); 
    changedrows := SQL%ROWCOUNT;
    commit;
exception
    when no_data_found then
        raise_application_error(-20146, 'Khong ton tai don ke thuoc nao voi ma tren');
end;
/
----------------------------------------------
-- 55
create or replace procedure proc_hoadonvienphi_layhoadonvienphi (p_result OUT SYS_REFCURSOR)
as
begin
    OPEN p_result FOR SELECT * FROM HOADONVIENPHI WHERE TONGTIEN > 0 ORDER BY TRANGTHAI, MAHD DESC;
end;
/
-- 56
create or replace procedure proc_hoadonvienphi_them1hoadonvienphi (
                                                    HDMaHD HOADONVIENPHI.MAHD%TYPE,
                                                    HDMaCa HOADONVIENPHI.MACA%TYPE,
                                                    HDNgayLap varchar,
                                                    HDGhiChu HOADONVIENPHI.GHICHU%TYPE,
                                                    changedrows OUT INT
                                                    )
as
begin
    INSERT INTO HOADONVIENPHI VALUES (HDMaHD, HDMaCa, TO_TIMESTAMP(HDNgayLap,'DD/MM/YYYY HH24:MI:SS'), 0, 0, 0, 0, HDGhiChu);
    changedrows := SQL%ROWCOUNT;
    commit;
end;
/
-- 57
create or replace procedure proc_hoadonvienphi_xoa1hoadonvienphi (
                                                    HDMaHD HOADONVIENPHI.MAHD%TYPE,
                                                    changedrows OUT INT
                                                    )
as
begin
    DELETE FROM HOADONVIENPHI 
        WHERE MAHD = HDMaHD;
    changedrows := SQL%ROWCOUNT;
    commit;
end;
/
-- 58
create or replace procedure proc_hoadonvienphi_sua1ghichu (
                                                    HDMaHD HOADONVIENPHI.MAHD%TYPE,
                                                    HDGhiChu HOADONVIENPHI.GHICHU%TYPE,
                                                    changedrows OUT INT
                                                    )
as
    v_mahd HOADONVIENPHI.MAHD%TYPE;
begin
    SELECT MAHD INTO v_mahd FROM HOADONVIENPHI WHERE MAHD = HDMaHD; 
    
    UPDATE HOADONVIENPHI
        SET GHICHU = HDGhiChu
        WHERE MAHD = HDMaHD;
    changedrows := SQL%ROWCOUNT;
    commit;
exception
    when no_data_found then
        raise_application_error(-20147, 'Khong ton tai hoa don nao voi ma tren');
end;
/
-- 59
create or replace procedure proc_hoadonvienphi_tinhhoadon (
                                                    HDMaHD HOADONVIENPHI.MAHD%TYPE,
                                                    HDMaCa HOADONVIENPHI.MACA%TYPE
                                                    )
as
    v_maphong CABENH.MAPHONG%TYPE;
    v_ngaychuyengannhat CABENH.NGAYCHUYENGANNHAT%TYPE;
    giathuephong PHONGBENH.GIA1NGAY%TYPE;
    v_tienthietbi HOADONVIENPHI.TIENKHAM%TYPE;
    v_tienbenh HOADONVIENPHI.TIENKHAM%TYPE;
    v_tienkham HOADONVIENPHI.TIENKHAM%TYPE;
    v_tienthuoc HOADONVIENPHI.TIENTHUOC%TYPE;
    v_tongtien HOADONVIENPHI.TONGTIEN%TYPE;
begin

    SELECT MAPHONG INTO v_maphong FROM CABENH WHERE MACA = HDMaCa;

    SELECT TIENKHAM, TONGTIEN INTO v_tienkham, v_tongtien FROM HOADONVIENPHI WHERE MAHD = HDMaHD;

    IF (v_tongtien = 0) THEN
        SELECT SUM(DPTB.SOLUONG * TB.GIA) INTO v_tienthietbi FROM DIEUPHOITHIETBI DPTB, THIETBIYTE TB
            WHERE DPTB.MACA = HDMaCa AND DPTB.MATHIETBI = TB.MATHIETBI;
    
        SELECT B.GIA INTO v_tienbenh FROM BENH B, CABENH CB WHERE CB.MACA = HDMaCa AND CB.MABENH = B.MABENH;
    
        SELECT SUM(KT.SL * TH.GIA) INTO v_tienthuoc FROM KETHUOC KT, THUOC TH
            WHERE KT.MACA = HDMaCa AND KT.MATHUOC = TH.MATHUOC;
        IF (v_tienthuoc is null) THEN
            v_tienthuoc := 0;
        END IF;
    
        SELECT NGAYCHUYENGANNHAT INTO v_ngaychuyengannhat FROM CABENH WHERE MACA = HDMaCa;
        IF (v_maphong is not null) THEN
            SELECT GIA1NGAY INTO giathuephong FROM PHONGBENH WHERE MAPHONG = v_maphong;
            v_tienkham := v_tienkham + (giathuephong * (TRUNC(CURRENT_TIMESTAMP) - TRUNC(v_ngaychuyengannhat)));
        END IF;
        IF (v_tienkham is null) THEN
            v_tienkham := 0;
        END IF;
    
        v_tienkham := v_tienkham + v_tienthietbi + v_tienbenh;
        v_tongtien := v_tongtien + v_tienkham + v_tienthuoc;
        
        UPDATE HOADONVIENPHI
            SET TONGTIEN = v_tongtien, TIENTHUOC = v_tienthuoc, TIENKHAM = v_tienkham
            WHERE MAHD = HDMaHD;
    END IF;
    commit;
end;
/
-- 60
create or replace procedure proc_hoadonvienphi_xacnhanthanhtoan (
                                                    HDMaHD HOADONVIENPHI.MAHD%TYPE,
                                                    changedrows OUT INT
                                                    )
as
    v_mahd HOADONVIENPHI.MAHD%TYPE;
begin
    SELECT MAHD INTO v_mahd FROM HOADONVIENPHI WHERE MAHD = HDMaHD; 
    
    UPDATE HOADONVIENPHI
        SET TRANGTHAI = 1
        WHERE MAHD = HDMaHD;
    changedrows := SQL%ROWCOUNT;
    commit;
exception
    when no_data_found then
        raise_application_error(-20148, 'Khong ton tai hoa don nao voi ma tren');
end;
/
-- 61
create or replace procedure proc_hoadonvienphi_layhoadonvienphi_theobenhnhan (BNMaBN BENHNHAN.MABN%TYPE, p_result OUT SYS_REFCURSOR)
as
begin
    OPEN p_result FOR SELECT HD.*, CB.NGAYBATDAU, BN.HOTEN, BN.GIOITINH, 
        (extract(year from (CURRENT_DATE)) - extract( year from (BN.NGAYSINH))) AS TUOI, BN.NOIOHIENTAI, BE.TENKHOA
        FROM HOADONVIENPHI HD, CABENH CB , BENHNHAN BN, BENH BE
        WHERE HD.TONGTIEN > 0 AND HD.MACA = CB.MACA AND CB.MABN = BNMaBN AND BN.MABN = BNMaBN AND CB.MABENH = BE.MABENH
        ORDER BY HD.MAHD DESC;
end;
/
----------------------------------------------                                        
-- 62
create or replace procedure proc_lichhenkham_laylichhenkham (p_result OUT SYS_REFCURSOR)
as
begin
    OPEN p_result FOR SELECT * FROM LICHHENKHAM ORDER BY MALICH DESC;
end;
/
-- 63
create or replace procedure proc_lichhenkham_them1lichhenkham (
                                                    LHMaLich LICHHENKHAM.MALICH%TYPE,
                                                    LHMaBN LICHHENKHAM.MABN%TYPE,
                                                    LHNgayDuKien varchar,
                                                    LHNhuCauKham LICHHENKHAM.NHUCAUKHAM%TYPE,
                                                    changedrows OUT INT
                                                    )
as
    ngaykhonghople EXCEPTION;
begin
    IF (TO_TIMESTAMP(LHNgayDuKien,'DD/MM/YYYY HH24:MI:SS') < CURRENT_TIMESTAMP) THEN
        RAISE ngaykhonghople;
    END IF;
    INSERT INTO LICHHENKHAM VALUES (LHMaLich, LHMaBN, '', TO_TIMESTAMP(LHNgayDuKien,'DD/MM/YYYY HH24:MI:SS'), LHNhuCauKham, 0, 0);
    changedrows := SQL%ROWCOUNT;
    commit;
exception
    when ngaykhonghople then
        raise_application_error(-20151, 'Ngay du kien kham phai lon hon hien tai');
end;
/
-- 64
create or replace procedure proc_lichhenkham_xoa1lichhenkham (
                                                    LHMaLich LICHHENKHAM.MALICH%TYPE,
                                                    changedrows OUT INT
                                                    )
as
begin
    DELETE FROM LICHHENKHAM
        WHERE MALICH = LHMaLich;
    changedrows := SQL%ROWCOUNT;
    commit;
end;
/
-- 65
create or replace procedure proc_lichhenkham_sua1lichhenkham (
                                                                LHMaLich LICHHENKHAM.MALICH%TYPE,
                                                                LHNgayDuKien varchar,
                                                                LHNhuCauKham LICHHENKHAM.NHUCAUKHAM%TYPE,
                                                                changedrows OUT INT
                                                                )
as
    v_malich LICHHENKHAM.MALICH%TYPE;
begin
    SELECT MALICH INTO v_malich FROM LICHHENKHAM WHERE MALICH = LHMaLich;
    
    UPDATE LICHHENKHAM
        SET NGAYDUKIEN = TO_TIMESTAMP(LHNgayDuKien,'DD/MM/YYYY HH24:MI:SS'), NHUCAUKHAM = LHNhuCauKham
        WHERE MALICH = LHMaLich;
    changedrows := SQL%ROWCOUNT;
    commit;

exception
    when no_data_found then
        raise_application_error(-20149, 'Khong ton tai lich hen kham nao voi ma tren');
end;
/
-- 66
create or replace procedure proc_lichhenkham_qlxacnhan (
                                                        LHMaLich LICHHENKHAM.MALICH%TYPE,
                                                        LHQLXacNhan LICHHENKHAM.QLXACNHAN%TYPE,
                                                        LHMaBS LICHHENKHAM.MABS%TYPE,
                                                        changedrows OUT INT
                                                        )
as
    thieuMaBS EXCEPTION;
begin
    IF (LHQLXacNhan = 1 AND (LHMaBS is null OR LHMaBS = '')) THEN
        RAISE thieuMaBS;
    END IF;
    UPDATE LICHHENKHAM
        SET QLXACNHAN = LHQLXacNhan, MABS = LHMaBS
        WHERE MALICH = LHMaLich;
    changedrows := SQL%ROWCOUNT;
    commit;
exception
     when thieuMaBS then
        raise_application_error(-20150, 'Lich khong the xac nhan khi thieu Ma Bac Si');
end;
/
-- 67
create or replace procedure proc_lichhenkham_bndaxem (
                                                        changedrows OUT INT
                                                        )
as
begin
    UPDATE LICHHENKHAM
        SET BNXACNHAN = 1
        WHERE QLXACNHAN > 0;
    changedrows := SQL%ROWCOUNT;
    commit;
end;
/
-- 68
create or replace procedure proc_lichhenkham_laylichhenkham_theobenhnhan (BNMaBN BENHNHAN.MABN%TYPE, p_result OUT SYS_REFCURSOR)
as
begin
    OPEN p_result FOR SELECT LH.*, BS.HOTEN FROM LICHHENKHAM LH LEFT JOIN BACSI BS ON BS.MABS = LH.MABS
        WHERE LH.MABN = BNMaBN
        ORDER BY MALICH DESC;
end;
/
-- 69
create or replace procedure proc_lichhenkham_laylichhenkham_theobacsi (BSMaBS BACSI.MABS%TYPE, p_result OUT SYS_REFCURSOR)
as
begin
    OPEN p_result FOR SELECT MALICH, MABN, NGAYDUKIEN, NHUCAUKHAM FROM LICHHENKHAM
        WHERE MABS = BSMaBS AND NGAYDUKIEN >= CURRENT_TIMESTAMP
        ORDER BY MALICH DESC;
end;
/
----------------------------------------------                                       
-- 70
create or replace procedure proc_kethuoc_laykethuocchitiet (p_result OUT SYS_REFCURSOR)
as
begin
    OPEN p_result FOR SELECT KT.MACA, BN.HOTEN, BS.HOTEN, KT.MATHUOC, TH.TENTHUOC, KT.NGAYKE, KT.SL FROM KETHUOC KT, CABENH CB, BACSI BS, BENHNHAN BN, THUOC TH
                        WHERE KT.MACA = CB.MACA AND CB.MABS = BS.MABS AND CB.MABN = BN.MABN AND KT.MATHUOC = TH.MATHUOC
                        ORDER BY KT.NGAYKE DESC;
end;
/
-- 71
create or replace procedure proc_lichhenkham_laylichhenkham_quanly (p_result OUT SYS_REFCURSOR)
as
begin
    OPEN p_result FOR SELECT LH.MALICH, LH.MABN, LH.MABS, BS.HOTEN, LH.NGAYDUKIEN, LH.NHUCAUKHAM FROM LICHHENKHAM LH, BACSI BS
                        WHERE LH.MABS = BS.MABS AND LH.QLXACNHAN = 1
                        ORDER BY LH.MALICH DESC;
end;
/
-- 72
create or replace procedure proc_kethuoc_laykethuoc_theoca (CBMaCa CABENH.MACA%TYPE, p_result OUT SYS_REFCURSOR)
as
begin
    OPEN p_result FOR SELECT TH.TENTHUOC, KT.SL FROM KETHUOC KT, THUOC TH
        WHERE MACA = CBMaCa AND TH.MATHUOC = KT.MATHUOC
        ORDER BY KT.NGAYKE DESC;
end;
/
-- 73
create or replace procedure proc_kethuoc_laykethuoc_theobs (BSMaBS BACSI.MABS%TYPE, p_result OUT SYS_REFCURSOR)
as
begin
    OPEN p_result FOR SELECT KT.MACA, KT.MATHUOC, TH.TENTHUOC, KT.NGAYKE, KT.SL FROM KETHUOC KT, CABENH CB, THUOC TH
        WHERE KT.MACA = CB.MACA AND CB.MABS = BSMaBS AND KT.MATHUOC = TH.MATHUOC
        ORDER BY KT.MACA ASC, KT.NGAYKE DESC;
end;
/
-------------------------
-- 74
create or replace procedure proc_thongke_laydoanhthu (nam number, thang number, p_result OUT SYS_REFCURSOR)
as
begin
    IF (nam = 0 AND thang = 0) THEN
        OPEN p_result FOR SELECT namlap, SUM(TONGTIEN) AS TONG FROM (
            SELECT (extract(year from NGAYLAP)) AS namlap, TONGTIEN
            FROM HOADONVIENPHI
            WHERE TRANGTHAI = 1 ) A
        GROUP BY namlap
        ORDER BY namlap ASC;
    ELSIF (nam = 0) THEN
        OPEN p_result FOR SELECT namlap, SUM(TONGTIEN) AS TONG FROM (
            SELECT (extract(year from NGAYLAP)) AS namlap, TONGTIEN
            FROM HOADONVIENPHI
            WHERE TRANGTHAI = 1 AND (extract (month from NGAYLAP)) = thang) A
        GROUP BY namlap
        ORDER BY namlap ASC;
    ELSIF (thang = 0) THEN
        OPEN p_result FOR SELECT thanglap, SUM(TONGTIEN) AS TONG FROM (
            SELECT (extract (month from NGAYLAP)) AS thanglap, TONGTIEN
            FROM HOADONVIENPHI
            WHERE TRANGTHAI = 1 AND (extract (year from NGAYLAP)) = nam) A
        GROUP BY thanglap
        ORDER BY thanglap ASC;
    ELSE
        OPEN p_result FOR SELECT ngaylap, SUM(TONGTIEN) AS TONG FROM (
            SELECT (extract(day from NGAYLAP)) AS ngaylap, TONGTIEN
            FROM HOADONVIENPHI
            WHERE TRANGTHAI = 1 AND (extract (year from NGAYLAP)) = nam AND (extract (month from NGAYLAP)) = thang) A
        GROUP BY ngaylap
        ORDER BY ngaylap ASC;
    END IF;
end;
/
-- 75
create or replace procedure proc_thongke_layluongcabenh (nam number, thang number, p_result OUT SYS_REFCURSOR)
as
begin
    IF (nam = 0 AND thang = 0) THEN
        OPEN p_result FOR SELECT nambd, COUNT(MACA) AS TONG FROM (
            SELECT (extract(year from NGAYBATDAU)) AS nambd, MACA
            FROM CABENH) A
        GROUP BY nambd
        ORDER BY nambd ASC;
    ELSIF (nam = 0) THEN
        OPEN p_result FOR SELECT nambd, COUNT(MACA) AS TONG FROM (
            SELECT (extract(year from NGAYBATDAU)) AS nambd, MACA
            FROM CABENH
            WHERE (extract (month from NGAYBATDAU)) = thang) A
        GROUP BY nambd
        ORDER BY nambd ASC;
    ELSIF (thang = 0) THEN
        OPEN p_result FOR SELECT thangbd, COUNT(MACA) AS TONG FROM (
            SELECT (extract (month from NGAYBATDAU)) AS thangbd, MACA
            FROM CABENH
            WHERE (extract (year from NGAYBATDAU)) = nam) A
        GROUP BY thangbd
        ORDER BY thangbd ASC;
    ELSE
        OPEN p_result FOR SELECT ngaybd, COUNT(MACA) AS TONG FROM (
            SELECT (extract(day from NGAYBATDAU)) AS ngaybd, MACA
            FROM CABENH
            WHERE (extract (year from NGAYBATDAU)) = nam AND (extract (month from NGAYBATDAU)) = thang) A
        GROUP BY ngaybd
        ORDER BY ngaybd ASC;
    END IF;
end;
/
-- 76
create or replace procedure proc_thongke_laytop5benh (nam number, thang number, p_result OUT SYS_REFCURSOR)
as
begin
    IF (thang = 0) THEN
        OPEN p_result FOR SELECT * FROM (SELECT tenbenh, COUNT(tenbenh) AS TONG FROM (
            SELECT BE.TENBENH
            FROM CABENH CB, BENH BE
            WHERE (extract (year from NGAYBATDAU)) = nam AND CB.MABENH = BE.MABENH) A
        GROUP BY tenbenh
        ORDER BY COUNT(tenbenh) DESC) WHERE ROWNUM <= 5 ORDER BY tenbenh ASC;
    ELSE
        OPEN p_result FOR SELECT * FROM (SELECT tenbenh, COUNT(tenbenh) AS TONG FROM (
            SELECT BE.TENBENH
            FROM CABENH CB, BENH BE
            WHERE (extract (year from NGAYBATDAU)) = nam AND (extract (month from NGAYBATDAU)) = thang AND CB.MABENH = BE.MABENH) A
        GROUP BY tenbenh
        ORDER BY COUNT(tenbenh) DESC) WHERE ROWNUM <= 5 ORDER BY tenbenh ASC;
    END IF;
end;
/
-- 77
create or replace procedure proc_thongke_laytop5no (nam number, thang number, p_result OUT SYS_REFCURSOR)
as
begin
    IF (thang = 0) THEN
        OPEN p_result FOR SELECT HOTEN, TONG FROM (SELECT MABN, HOTEN, SUM(TONGTIEN) AS TONG FROM (
            SELECT CB.MABN, BN.HOTEN, HD.TONGTIEN
            FROM HOADONVIENPHI HD, CABENH CB, BENHNHAN BN
            WHERE (extract (year from NGAYLAP)) = nam AND CB.MACA = HD.MACA AND CB.MABN = BN.MABN
                    AND HD.TONGTIEN > 0 AND HD.TRANGTHAI = 0) A
        GROUP BY MABN, HOTEN
        ORDER BY SUM(TONGTIEN) DESC) WHERE ROWNUM <= 5 ORDER BY HOTEN ASC;
    ELSE
        OPEN p_result FOR SELECT HOTEN, TONG FROM (SELECT MABN, HOTEN, SUM(TONGTIEN) AS TONG FROM (
            SELECT CB.MABN, BN.HOTEN, HD.TONGTIEN
            FROM HOADONVIENPHI HD, CABENH CB, BENHNHAN BN
            WHERE (extract (year from NGAYLAP)) = nam AND (extract (month from NGAYLAP)) = thang
                AND CB.MACA = HD.MACA AND CB.MABN = BN.MABN AND HD.TONGTIEN > 0 AND HD.TRANGTHAI = 0) A
        GROUP BY MABN, HOTEN
        ORDER BY SUM(TONGTIEN) DESC) WHERE ROWNUM <= 5 ORDER BY HOTEN ASC;
    END IF;
end;
/
-- 78
create or replace procedure proc_thongke_laynam (p_result OUT SYS_REFCURSOR)
as
begin
       OPEN p_result FOR SELECT nambd FROM (
            SELECT DISTINCT(extract(year from NGAYBATDAU)) AS nambd
            FROM CABENH) A
        ORDER BY nambd DESC;
end;
/
