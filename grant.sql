grant execute on proc_thuoc_laythuoc to quanlyrole;
grant execute on proc_thuoc_them1thuoc to quanlyrole;
grant execute on proc_thuoc_sua1thuoc to quanlyrole;
grant execute on proc_thuoc_xoa1thuoc to quanlyrole;

grant execute on proc_kethuoc_laykethuocchitiet to quanlyrole;
grant execute on proc_lichhenkham_laylichhenkham_quanly to quanlyrole;
grant execute on proc_lichhenkham_laylichhenkham to quanlyrole;
grant execute on proc_lichhenkham_qlxacnhan to quanlyrole;
grant execute on proc_thongke_laydoanhthu to quanlyrole;
grant execute on proc_thongke_layluongcabenh to quanlyrole;
grant execute on proc_thongke_laytop5benh to quanlyrole;
grant execute on proc_thongke_laytop5no to quanlyrole;
grant execute on proc_thongke_laynam to quanlyrole;
grant execute on proc_hoadonvienphi_layhoadonvienphi to quanlyrole;
grant execute on proc_hoadonvienphi_xacnhanthanhtoan to quanlyrole;

grant quanlyrole to quanly;


grant execute on proc_thuoc_laythuoc to bacsirole;
grant execute on proc_kethuoc_them1kethuoc to bacsirole;
grant execute on proc_lichhenkham_laylichhenkham_theobacsi to bacsirole;
grant execute on proc_kethuoc_laykethuoc_theobs to bacsirole;
grant execute on proc_kethuoc_laykethuoc_theoca to bacsirole;

grant bacsirole to bacsi;

grant execute on proc_lichhenkham_laylichhenkham_theobenhnhan to benhnhanrole;
grant execute on proc_hoadonvienphi_layhoadonvienphi_theobenhnhan to benhnhanrole;
grant execute on proc_lichhenkham_xoa1lichhenkham to benhnhanrole;
grant execute on proc_lichhenkham_them1lichhenkham to benhnhanrole;
grant execute on proc_lichhenkham_laylichhenkham to benhnhanrole;
grant execute on proc_lichhenkham_bndaxem to benhnhanrole;

grant benhnhanrole to benhnhan;