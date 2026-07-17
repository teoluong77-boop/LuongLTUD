Câu 1: JpaRepository kế thừa các interface nào? Hãy nêu sự khác biệt lớn nhất giữa findAll() của CrudRepository và findAll() của JpaRepository.

Các interface mà JpaRepository kế thừa:

ListCrudRepository

ListPagingAndSortingRepository

QueryByExampleExecutor(Nói cách khác, nó kế thừa gián tiếp từ CrudRepository và PagingAndSortingRepository của Spring Data).

Sự khác biệt lớn nhất giữa hàm findAll():

CrudRepository.findAll(): Trả về một đối tượng kiểu Iterable (vòng lặp thuần túy). Khi sử dụng, lập trình viên thường phải ép kiểu hoặc duyệt mảng khá bất tiện.

JpaRepository.findAll(): Được ghi đè (override) để trả về hẳn một đối tượng kiểu List (Danh sách của Java). Điều này giúp lập trình viên dễ dàng sử dụng các hàm tiện ích của List hoặc Stream API ngay lập tức mà không cần ép kiểu.

Câu 2: Hãy viết một câu SQL mô phỏng có mệnh đề LIMIT và OFFSET tương ứng khi client yêu cầu page = 2, size = 5 (lưu ý: số trang bắt đầu từ 0).
Công thức tính OFFSET: OFFSET = page * size = 2 * 5 = 10.

Câu lệnh SQL mô phỏng tương ứng (ví dụ áp dụng với bảng products):

SELECT * FROM products LIMIT 5 OFFSET 10;

Câu 3: Lỗi N+1 query là gì? Tại sao việc thiết lập FetchType.EAGER cho các collection quan hệ @OneToMany lại là nguyên nhân chính dẫn đến lỗi này?

Lỗi N+1 query là gì:

Là hiện tượng hiệu năng bị giảm sút nghiêm trọng khi ứng dụng muốn lấy một danh sách dữ liệu gồm N phần tử, nhưng Hibernate lại phải thực hiện tổng cộng N + 1 câu lệnh SQL xuống database. Trong đó: 1 câu lệnh ban đầu để lấy ra danh sách chính, và N câu lệnh độc lập tiếp theo chỉ để lấy dữ liệu quan hệ liên quan của từng phần tử một.

Tại sao FetchType.EAGER cho @OneToMany lại là nguyên nhân chính:

Khi cấu hình FetchType.EAGER, bạn đang ép Hibernate bắt buộc phải tải ngay lập tức toàn bộ dữ liệu của collection quan hệ đi kèm (ví dụ: lấy danh sách Category thì phải lấy luôn danh sách các Product thuộc từng Category đó).

Do đó, sau khi chạy 1 câu lệnh SELECT để lấy ra tất cả danh sách cha (Categories), Hibernate sẽ lập tức tự động chạy thêm N câu lệnh SELECT phụ để gom các con (Products) tương ứng với từng cha một, tạo nên lỗi N+1 query một cách tự động mà lập trình viên không hề chủ ý gọi.

Câu 4: Trong quan hệ hai chiều, từ khóa mappedBy có tác dụng gì? Nếu quên khai báo mappedBy ở phía Inverse Side thì Hibernate sẽ xử lý như thế nào và gây ra hệ quả gì dưới Database?

Tác dụng của từ khóa mappedBy:

Được dùng để đánh dấu phía Inverse Side (phía không sở hữu quan hệ), chỉ ra rằng mối quan hệ này đã được định nghĩa và quản lý bởi một thuộc tính ở phía bên kia (Owning Side). Nó giúp Hibernate hiểu đây là mối quan hệ hai chiều trên cùng một liên kết, tránh việc hiểu lầm thành hai mối quan hệ một chiều độc lập.

Nếu quên khai báo mappedBy ở phía Inverse Side:

Cách xử lý của Hibernate: Hibernate sẽ mặc định hiểu rằng đây là hai mối quan hệ một chiều riêng biệt hoàn toàn.

Hệ quả dưới Database: Để liên kết hai mối quan hệ này, Hibernate sẽ tự động tạo thêm một "bảng trung gian" (Join Table) dư thừa ngoài ý muốn để lưu khóa ngoại của hai bảng, hoặc tự động tạo thêm một cột khóa ngoại không cần thiết, làm xấu cấu trúc cơ sở dữ liệu và khiến các câu lệnh INSERT/UPDATE sau này bị rối, chạy sai logic mong muốn.