Câu 1: Phân biệt JDBC và ORM: Em hãy liệt kê ít nhất 3 hạn chế cốt lõi của JDBC truyền thống mà ORM giúp giải quyết. 
Cho ví dụ minh họa cho mỗi hạn chế.  
Hạn chế 1: Phải viết thủ công các câu lệnh SQL dài dòng, lặp đi lặp lại (Boilerplate code).  
JDBC: Lập trình viên phải tự viết tay mọi câu lệnh INSERT, SELECT dưới dạng chuỗi (String). Ví dụ: phải tự gõ "INSERT INTO products (product_name, price) VALUES (?, ?)" rất dễ sai chính tả.  
ORM giải quyết: Tự động sinh câu lệnh SQL tương ứng dựa trên cấu trúc đối tượng Entity đang thao tác. Khi thêm mới, ta chỉ cần gọi một dòng lệnh Java đơn giản như repository.save(product).  
Hạn chế 2: Phải chuyển đổi dữ liệu thủ công từ cơ sở dữ liệu lên đối tượng Java (Mapping).  
JDBC: Phải duyệt qua vòng lặp ResultSet rồi gán thủ công từng cột vào đối tượng Java (ví dụ: p.setName(resultSet.getString("product_name"))).  
ORM giải quyết: Tự động ánh xạ (mapping) trực tiếp 1 dòng trong bảng cơ sở dữ liệu thành 1 thực thể (Object) Java tương ứng.  
Hạn chế 3: Khó thay đổi hệ quản trị cơ sở dữ liệu (Database Vendor Dependency).  
JDBC: Cú pháp SQL giữa các cơ sở dữ liệu (MySQL, Oracle, SQL Server) có sự khác biệt. Nếu đổi database, bạn phải sửa lại hầu hết các câu lệnh SQL trong code JDBC.  
ORM giải quyết: Sử dụng cơ chế Dialect (phát ngôn) để tự dịch các hành động Java thành câu lệnh SQL đúng cú pháp của hệ cơ sở dữ liệu đang kết nối.  
Câu 2: Kiến trúc phân tầng: Em hãy giải thích mối quan hệ giữa Spring Data JPA, JPA, Hibernate và JDBC. Tại sao Spring Data JPA không thể hoạt động nếu không có Hibernate?  
Mối quan hệ giữa các tầng từ cao xuống thấp:  
Spring Data JPA: Là thư viện bọc ngoài cùng, cung cấp các interface tiện lợi như JpaRepository để lập trình viên sử dụng nhanh chóng.  
JPA: Là bộ đặc tả / tiêu chuẩn chung của Java (gồm các Annotation như @Entity, @Table) nhưng không có code thực thi.  
Hibernate: Là bộ hiện thực hóa của JPA (ORM Provider), trực tiếp dịch các thao tác Java thành câu lệnh SQL cụ thể.  
JDBC: Là tầng kết nối vật lý thấp nhất để trực tiếp gửi câu lệnh SQL vào database MySQL.  
Tại sao Spring Data JPA không thể hoạt động nếu không có Hibernate?  
Vì Spring Data JPA chỉ là lớp giao diện trừu tượng ở tầng cao để rút gọn code. Nó bắt buộc phải có một bộ hiện thực hóa (ORM Provider) như Hibernate đứng sau làm nhiệm vụ kết nối, dịch mã và thực thi các câu lệnh xuống database thực tế.  
Câu 3: Constructor không tham số: Tại sao Entity class bắt buộc phải có constructor không tham số? Điều gì xảy ra nếu lập trình viên quên khai báo constructor này?  Tại sao Entity class bắt buộc phải có constructor không tham số?  
Vì Hibernate sử dụng cơ chế phản chiếu (Reflection API) của Java để khởi tạo đối tượng Entity khi lấy dữ liệu từ database lên. Đầu tiên, nó gọi constructor không tham số để tạo một thực thể rỗng, sau đó mới lấp đầy dữ liệu từ các cột vào thực thể đó.  
Điều gì xảy ra nếu lập trình viên quên khai báo constructor này?  
Nếu trong class đã khai báo constructor có tham số mà quên viết constructor không tham số (hoặc không dùng @NoArgsConstructor của Lombok), Java sẽ không tự tạo constructor mặc định nữa. Khi chạy ứng dụng, Hibernate sẽ báo lỗi nghiêm trọng và ứng dụng bị dừng (crash) ngay khi có thao tác truy vấn dữ liệu: org.hibernate.InstantiationException: No default constructor for entity.  
Câu 4: Chế độ ddl-auto: Chế độ ddl-auto nào cực kỳ nguy hiểm trong môi trường Production? Giải thích tại sao và nêu chế độ phù hợp cho Production.  
Chế độ cực kỳ nguy hiểm trong Production: Chế độ create và create-drop.  
Giải thích: Vì mỗi khi khởi động hoặc tắt ứng dụng, Hibernate sẽ tự động thực hiện lệnh xóa bảng (DROP TABLE IF EXISTS) khiến toàn bộ dữ liệu thực tế của doanh nghiệp bị xóa sạch vĩnh viễn.  
Chế độ phù hợp cho Production:  
none (hoặc không khai báo): An toàn nhất, cấu trúc database sẽ được quản lý bằng các file script SQL hoặc công cụ chuyên dụng như Flyway/Liquibase. 
validate: Chỉ kiểm tra tính đồng nhất giữa code Java và database thực tế, nếu lệch cấu trúc sẽ báo lỗi dừng chương trình chứ không tự ý xóa hay sửa dữ liệu.  
Câu 5: Annotation @Column: Em hãy giải thích ý nghĩa của từng thuộc tính: name, nullable, unique, length, precision, scale trong annotation @Column. Tại sao nên sử dụng BigDecimal thay vì double cho kiểu tiền tệ?  
Ý nghĩa của các thuộc tính:  
name: Định nghĩa tên của cột tương ứng trong bảng cơ sở dữ liệu.  
nullable: Quy định cột có được phép nhận giá trị rỗng (NULL) hay không (mặc định là true).  
unique: Quy định giá trị trong cột phải là duy nhất, không được trùng lặp (mặc định là false).  
length: Quy định độ dài tối đa của cột dạng chuỗi (VARCHAR), mặc định là 255 ký tự.  
precision: Tổng số chữ số có thể hiển thị của số thập phân lớn.  
scale: Số lượng chữ số nằm sau dấu phẩy thập phân.  
Tại sao nên sử dụng BigDecimal thay vì double cho kiểu tiền tệ?  
Vì kiểu dữ liệu double biểu diễn số thực bằng dấu phẩy động nhị phân nên dễ phát sinh sai số làm tròn khi thực hiện tính toán số học phức tạp (ví dụ: 0.1 + 0.2 có thể ra 0.30000000000000004). Trong các nghiệp vụ liên quan đến tiền tệ, sai số dù nhỏ nhất cũng không được chấp nhận, do đó BigDecimal được sử dụng nhờ khả năng tính toán chính xác tuyệt đối hệ cơ số 10. 