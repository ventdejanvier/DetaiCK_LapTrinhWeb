-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 15, 2024 at 07:49 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tuvan`
--

-- --------------------------------------------------------

--
-- Table structure for table `consultant`
--

CREATE TABLE `consultant` (
  `id` bigint(20) NOT NULL,
  `created_date_time` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `modified_date_time` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `full_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `consultant`
--

INSERT INTO `consultant` (`id`, `created_date_time`, `created_by`, `status`, `modified_date_time`, `updated_by`, `address`, `dob`, `full_name`, `category_id`, `user_id`) VALUES
(3, NULL, NULL, NULL, NULL, NULL, '3602 Gaylord Dr', '2024-12-03', 'Nguyễn Thuỳ Trang', 1, 6),
(4, NULL, NULL, NULL, NULL, NULL, '12 Lê Duẩn, HN', '2024-11-29', 'Phạm Văn Châu', 2, 7);

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
  `id` bigint(20) NOT NULL,
  `created_date_time` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `modified_date_time` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `full_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`id`, `created_date_time`, `created_by`, `status`, `modified_date_time`, `updated_by`, `address`, `dob`, `full_name`, `user_id`) VALUES
(1, NULL, 'SUPER_ADMIN', 1, NULL, 'SUPER_ADMIN', '3602 Gaylord Dr', NULL, 'Nguyễn Thuỳ Trang', 1),
(2, NULL, 'SUPER_ADMIN', 1, '2024-12-15 18:26:38.000000', 'chuyendizz@gmail.com', '3602 Gaylord Dr', '2000-12-02', 'Hoàng Văn Vinh', 3),
(3, NULL, 'SUPER_ADMIN', 1, NULL, 'SUPER_ADMIN', '12 HN, Giải Phóng', NULL, 'Phạm Văn Đ', 8);

-- --------------------------------------------------------

--
-- Table structure for table `department`
--

CREATE TABLE `department` (
  `id` bigint(20) NOT NULL,
  `created_date_time` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `modified_date_time` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `department`
--

INSERT INTO `department` (`id`, `created_date_time`, `created_by`, `status`, `modified_date_time`, `updated_by`, `name`) VALUES
(1, NULL, NULL, 1, NULL, NULL, 'Khoa Công Nghệ Thông Tin'),
(2, NULL, NULL, 1, NULL, NULL, 'Khoa Kinh Tế');

-- --------------------------------------------------------

--
-- Table structure for table `question`
--

CREATE TABLE `question` (
  `id` bigint(20) NOT NULL,
  `created_date_time` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `modified_date_time` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `reply` text DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `consultant_id` bigint(20) DEFAULT NULL,
  `customer_id` bigint(20) DEFAULT NULL,
  `department_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `question`
--

INSERT INTO `question` (`id`, `created_date_time`, `created_by`, `status`, `modified_date_time`, `updated_by`, `content`, `reply`, `title`, `consultant_id`, `customer_id`, `department_id`) VALUES
(1, NULL, NULL, NULL, NULL, NULL, NULL, '<p>Câu hỏi rất hay!!!</p><figure class=\"image\"><img src=\"data:image/webp;base64,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LqPz5jls18DGoPvB9l1fEoRhG1Fe98SM4tfpkfCfGlBjDzQC4C1KYLJP9gjGpcP4PIoomnHiVtOxbTJv30qvV8WzTe14FrhlZU5x3PZEgSYCmn4gv+4ZoUcJ2l9wegDu5sxN1povkYQG2YA+Ppfq/GAAAABFWElGkQEAAEV4aWYAAElJKgAIAAAABQAOAQIAIAEAAEoAAACYggIAEQAAAGoBAAAaAQUAAQAAAHsBAAAbAQUAAQAAAIMBAAASAQMAAQAAAAEAAAAAAAAAQlVFTk9TIEFJUkVTLCBBUkdFTlRJTkEgLSBOT1ZFTUJFUiAxOTogTGlvbmVsIE1lc3NpIG9mIEFyZ2VudGluYSB3YXZlcyB0byBmYW5zIHByaW9yIHRvIHRoZSBTb3V0aCBBbWVyaWNhbiBGSUZBIFdvcmxkIEN1cCAyMDI2IFF1YWxpZmllciBtYXRjaCBiZXR3ZWVuIEFyZ2VudGluYSBhbmQgUGVydSBhdCBFc3RhZGlvIEFsYmVydG8gSi4gQXJtYW5kbyBvbiBOb3ZlbWJlciAxOSwgMjAyNCBpbiBCdWVub3MgQWlyZXMsIEFyZ2VudGluYS4gKFBob3RvIGJ5IE1hcmNlbG8gRW5kZWxsaS9HZXR0eSBJbWFnZXMpMjAyNCBHZXR0eSBJbWFnZXMsAQAAAQAAACwBAAABAAAAAFhNUCCWBgAAaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wLwA8P3hwYWNrZXQgYmVnaW49Iu+7vyIgaWQ9Ilc1TTBNcENlaGlIenJlU3pOVGN6a2M5ZCI/Pgo8eDp4bXBtZXRhIHhtbG5zOng9ImFkb2JlOm5zOm1ldGEvIj4KCTxyZGY6UkRGIHhtbG5zOnJkZj0iaHR0cDovL3d3dy53My5vcmcvMTk5OS8wMi8yMi1yZGYtc3ludGF4LW5zIyI+CgkJPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6cGhvdG9zaG9wPSJodHRwOi8vbnMuYWRvYmUuY29tL3Bob3Rvc2hvcC8xLjAvIiB4bWxuczpJcHRjNHhtcENvcmU9Imh0dHA6Ly9pcHRjLm9yZy9zdGQvSXB0YzR4bXBDb3JlLzEuMC94bWxucy8iICAgeG1sbnM6R2V0dHlJbWFnZXNHSUZUPSJodHRwOi8veG1wLmdldHR5aW1hZ2VzLmNvbS9naWZ0LzEuMC8iIHhtbG5zOmRjPSJodHRwOi8vcHVybC5vcmcvZGMvZWxlbWVudHMvMS4xLyIgeG1sbnM6cGx1cz0iaHR0cDovL25zLnVzZXBsdXMub3JnL2xkZi94bXAvMS4wLyIgIHhtbG5zOmlwdGNFeHQ9Imh0dHA6Ly9pcHRjLm9yZy9zdGQvSXB0YzR4bXBFeHQvMjAwOC0wMi0yOS8iIHhtbG5zOnhtcFJpZ2h0cz0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL3JpZ2h0cy8iIGRjOlJpZ2h0cz0iMjAyNCBHZXR0eSBJbWFnZXMiIHBob3Rvc2hvcDpDcmVkaXQ9IkdldHR5IEltYWdlcyIgR2V0dHlJbWFnZXNHSUZUOkFzc2V0SUQ9IjIxODU2NDQ0OTgiIHhtcFJpZ2h0czpXZWJTdGF0ZW1lbnQ9Imh0dHBzOi8vd3d3LmdldHR5aW1hZ2VzLmNvbS9ldWxhP3V0bV9tZWRpdW09b3JnYW5pYyZhbXA7dXRtX3NvdXJjZT1nb29nbGUmYW1wO3V0bV9jYW1wYWlnbj1pcHRjdXJsIiBwbHVzOkRhdGFNaW5pbmc9Imh0dHA6Ly9ucy51c2VwbHVzLm9yZy9sZGYvdm9jYWIvRE1JLVBST0hJQklURUQtRVhDRVBUU0VBUkNIRU5HSU5FSU5ERVhJTkciID4KPGRjOmNyZWF0b3I+PHJkZjpTZXE+PHJkZjpsaT5NYXJjZWxvIEVuZGVsbGk8L3JkZjpsaT48L3JkZjpTZXE+PC9kYzpjcmVhdG9yPjxkYzpkZXNjcmlwdGlvbj48cmRmOkFsdD48cmRmOmxpIHhtbDpsYW5nPSJ4LWRlZmF1bHQiPkJVRU5PUyBBSVJFUywgQVJHRU5USU5BIC0gTk9WRU1CRVIgMTk6IExpb25lbCBNZXNzaSBvZiBBcmdlbnRpbmEgd2F2ZXMgdG8gZmFucyBwcmlvciB0byB0aGUgU291dGggQW1lcmljYW4gRklGQSBXb3JsZCBDdXAgMjAyNiBRdWFsaWZpZXIgbWF0Y2ggYmV0d2VlbiBBcmdlbnRpbmEgYW5kIFBlcnUgYXQgRXN0YWRpbyBBbGJlcnRvIEouIEFybWFuZG8gb24gTm92ZW1iZXIgMTksIDIwMjQgaW4gQnVlbm9zIEFpcmVzLCBBcmdlbnRpbmEuIChQaG90byBieSBNYXJjZWxvIEVuZGVsbGkvR2V0dHkgSW1hZ2VzKTwvcmRmOmxpPjwvcmRmOkFsdD48L2RjOmRlc2NyaXB0aW9uPgo8cGx1czpMaWNlbnNvcj48cmRmOlNlcT48cmRmOmxpIHJkZjpwYXJzZVR5cGU9J1Jlc291cmNlJz48cGx1czpMaWNlbnNvclVSTD5odHRwczovL3d3dy5nZXR0eWltYWdlcy5jb20vZGV0YWlsLzIxODU2NDQ0OTg/dXRtX21lZGl1bT1vcmdhbmljJmFtcDt1dG1fc291cmNlPWdvb2dsZSZhbXA7dXRtX2NhbXBhaWduPWlwdGN1cmw8L3BsdXM6TGljZW5zb3JVUkw+PC9yZGY6bGk+PC9yZGY6U2VxPjwvcGx1czpMaWNlbnNvcj4KCQk8L3JkZjpEZXNjcmlwdGlvbj4KCTwvcmRmOlJERj4KPC94OnhtcG1ldGE+Cjw/eHBhY2tldCBlbmQ9InciPz4K\" alt=\"BUENOS AIRES, ARGENTINA - NOVEMBER 19: Lionel Messi of Argentina waves to fans prior to the South American FIFA World Cup 2026 Qualifier match between Argentina and Peru at Estadio Alberto J. Armando on November 19, 2024 in Buenos Aires, Argentina. (Photo by Marcelo Endelli/Getty Images)\"></figure>', NULL, 3, NULL, NULL),
(3, NULL, NULL, NULL, NULL, NULL, 'Khoa mình có hoạt động gì Tết không ạ?', '<h2>Trường Đại học Hàng hải Việt Nam ghi dấu ấn tại Giải thưởng sinh viên nghiên cứu khoa học - Euréka lần thứ 26</h2><p>Ngày 08/12/2024, Thành đoàn Thành phố Hồ Chí Minh phối hợp cùng Đại học Quốc gia thành phố Hồ Chí Minh tổ chức <strong>Lễ tổng kết trao giải Giải thưởng sinh viên nghiên cứu khoa học (NCKH) - Euréka lần thứ 26 năm 2024</strong>. Đây là Giải thưởng khoa học uy tín dành cho các sinh viên đam mê NCKH tại các trường đại học, học viện và cao đẳng trên toàn quốc. Qua 26 năm tổ chức, Giải thưởng đã lan tỏa mạnh mẽ phong trào NCKH trong sinh viên, từ đó ươm mầm nhiều nhà khoa học trẻ từ khi còn trên ghế nhà trường, góp phần đào tạo đội ngũ các nhà khoa học trong tương lai.</p><p>Giải thưởng sinh viên NCKH - Euréka lần thứ 26 năm 2024 được triển khai và thực hiện từ tháng 07 đến tháng 12/2024. Đối tượng tham gia Giải thưởng là sinh viên hiện đang học tập tại các trường đại học, học viện và cao đẳng trên toàn quốc, có thể đăng ký cá nhân hoặc theo nhóm. Giải thưởng năm nay có tổng cộng 15 lĩnh vực, bao gồm: công nghệ thông tin; công nghệ thực phẩm; hành chính - pháp lý; hóa học; khoa học giáo dục; khoa học nông nghiệp; khoa học xã hội; khoa học y - dược; kinh tế; kỹ thuật công nghệ; quy hoạch, kiến trúc và xây dựng; sinh học; tài nguyên và môi trường; văn hóa - nghệ thuật; vật lý.</p><p>Để đến với Giải thưởng các đề tài NCKH của sinh viên phải trải qua những vòng tuyển chọn khắt khe ở cấp trường, được hội đồng khoa học nhà trường đánh giá, thông qua và giới thiệu đăng ký tham gia vòng bán kết Giải thưởng tại Thành phố Hồ Chí Minh. Nhờ sự quan tâm và đầu tư đặc biệt đó mà Giải thưởng ngày càng được nâng cao cả về quy mô và chất lượng. Năm 2024, Giải thưởng đã thu hút 1.903 đề tài của sinh viên 152 trường đại học, học viện và cao đẳng.</p><p>Trường Đại học Hàng hải Việt Nam tự hào có 01 đề tài xuất sắc lọt vào vòng chung kết, thuộc lĩnh vực kỹ thuật công nghệ. Vòng chung kết diễn ra từ ngày 06 - 08/12/2024 tại Trường Đại học Sài Gòn (Quận 5, Tp. Hồ Chí Minh), với sự góp mặt của 187 đề tài đến từ 101 trường trên toàn quốc. Các đề tài tham gia tại Vòng chung kết năm nay có tính thời sự, tính ứng dụng cao, nhiều đề tài nghiên cứu ứng dụng trí tuệ nhân tạo AI trên nhiều lĩnh vực; nhiều đề tài nghiên cứu về các vấn đề nóng của xã hội, và có những đề tài được chuyển giao công nghệ với nhiều công trình có tiềm năng và thương mại hóa sản phẩm. Ngoài phần thi chuyên môn, thí sinh còn được tham gia các hoạt động giao lưu bổ ích như thuyết trình trước Hội đồng khoa học, tham quan các công trình trọng điểm và di tích lịch sử tại thành phố Hồ Chí Minh, cùng hội trại sinh viên NCKH Euréka.</p><p>Một lần nữa, đề tài <i>“Nghiên cứu, thiết kế và chế tạo trạm lắp ráp thủ công có thể tùy biến theo nhân trắc học và tích hợp giám sát thao tác phục vụ cải tiến nâng cao hiệu suất làm việc cho công nhân”</i> đến từ Khoa Máy tàu biển, Trường Đại học Hàng hải Việt Nam của nhóm sinh viên Ngô Vương Quốc, Nguyễn Thị Minh Anh, Phạm Văn Quỳnh dưới sự hướng dẫn của TS. Phạm Văn Triệu - giảng viên đã được vinh danh với giải Nhất.</p><p><img src=\"https://fit.vimaru.edu.vn/sites/default/files/u648/ghep_7.jpg\" alt=\"\"></p><p><img src=\"https://fit.vimaru.edu.vn/sites/default/files/u648/A7400238%20700.jpg\" alt=\"\"></p><p>&nbsp; &nbsp;<i>Một số hình ảnh tại Giải thưởng sinh viên NCKH - Euréka lần thứ 26</i></p><p>Giải thưởng sinh viên NCKH - Euréka lần thứ 26 năm 2024 đã phát huy năng lực tư duy sáng tạo, chủ động trong học tập, nghiên cứu của sinh viên, góp phần ứng dụng kiến thức trong Nhà trường giải quyết các vấn đề thực tiễn cuộc sống, đất nước. Đây cũng là động lực thúc đẩy phong trào NCKH trong sinh viên, hướng tới những thành tựu cao hơn trong tương lai; góp phần khẳng định vai trò, vị thế của Trường Đại học Hàng hải Việt Nam trong hành trình gần 70 năm xây dựng, trưởng thành và phát triển đồng thời góp phần vào sự phát triển chung của thành phố Hải Phòng và của đất nước Việt Nam.</p>', 'Khoa mình có hoạt động gì Tết không ạ?', 3, 2, 1),
(4, NULL, NULL, NULL, NULL, NULL, '<h2>Được nghỉ mấy ngày Tết tây?</h2>', '<p>Thưởng Tết Dương năm 2025 là một khoản tiền thưởng dành cho người lao động ngay sau khi kết thúc năm 2024 dương lịch và khoản tiền này cũng không phải là tiền lương tháng thứ 13.</p><p>Theo quy định, tiền thưởng tết Dương không là khoản thưởng bắt buộc doanh nghiệp phải có cho người lao động nhưng chính sách thưởng cần phải được ghi rõ trong hợp đồng lao động để người lao động được biết theo quy định tại Điểm a, Khoản 1, Điều 17, Nghị định số 12/2022/NĐ-CP.</p><p>Bên canh đó, số tiền cụ thể và điều kiện thưởng còn tùy thuộc vào chính sách thưởng của mỗi doanh nghiệp và ngành nghề.</p><p>Doanh nghiệp có thể lựa chọn hình thức thưởng Tết Dương 2025 cho người lao động là tiền, hiện vật hoặc cả hai tùy theo tình hình kinh doanh của doanh nghiệp và năng suất làm việc của người lao động.</p>', 'Được nghỉ mấy ngày Tết tây?', 4, 2, 2);

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `id` bigint(20) NOT NULL,
  `created_date_time` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `modified_date_time` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`id`, `created_date_time`, `created_by`, `status`, `modified_date_time`, `updated_by`, `name`) VALUES
(1, NULL, NULL, 1, NULL, NULL, 'ADMIN'),
(2, NULL, NULL, 1, NULL, NULL, 'CONSULTANT'),
(3, NULL, NULL, 1, NULL, NULL, 'CLIENT');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `created_date_time` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `modified_date_time` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `avatar` text CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(120) DEFAULT NULL,
  `phone_number` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `created_date_time`, `created_by`, `status`, `modified_date_time`, `updated_by`, `avatar`, `email`, `password`, `phone_number`) VALUES
(1, NULL, 'SUPER_ADMIN', 1, NULL, 'SUPER_ADMIN', NULL, 'phamminhhiep301@gmail.com', '$2a$10$Mt0wHYhP.gnn1Csy0DDCrepMEKigqJYeLMSF8szc7iaLFAibRGREq', '6308348000'),
(2, NULL, NULL, 1, NULL, NULL, NULL, 'admin', '$2a$12$dlGpaPI453JPsrTXoa2r5ua2Pbu12rpudyRJtESnGbXU0M0xFAaoS', NULL),
(3, NULL, 'SUPER_ADMIN', 1, NULL, 'SUPER_ADMIN', 'https://onlineshopswp391g1.s3-ap-southeast-1.amazonaws.com/user_3?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20241215T161554Z&X-Amz-SignedHeaders=host&X-Amz-Expires=604800&X-Amz-Credential=AKIA4MTWGWUSPDNXQGTX%2F20241215%2Fap-southeast-1%2Fs3%2Faws4_request&X-Amz-Signature=abd4a1f07c0c7607e979b162344e39212b97268bdd498fce5b47e5a8c2997849', 'chuyendizz@gmail.com', '$2a$10$n51xxynMe/qaI96lTtxC6uK8jroXTNl5TlbZnK.Kjrt0vW/BImKhq', '6308348000'),
(6, NULL, NULL, 1, '2024-12-15 18:20:19.000000', 'ntt@gmail.com', 'https://onlineshopswp391g1.s3-ap-southeast-1.amazonaws.com/user_6?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20241215T182019Z&X-Amz-SignedHeaders=host&X-Amz-Expires=604800&X-Amz-Credential=AKIA4MTWGWUSPDNXQGTX%2F20241215%2Fap-southeast-1%2Fs3%2Faws4_request&X-Amz-Signature=fe7a021c40e099a61afa65ee5a7c8e20513c22673ae5b731699f164da54eb5fb', 'ntt@gmail.com', '$2a$10$JXvIX3dIRk3pMxFeErWL4ObzIPYvdQWAvNTd8DFFVv1g2Wsd7hsC.', '6308348000'),
(7, NULL, NULL, 1, NULL, NULL, NULL, 'pvc@gmail.com', '$2a$10$E5DEXHBPvKs36o.ykOvjMurMRBJQQk5ilzKePjSzCH.8J7kUp1VrK', '0912312313'),
(8, NULL, 'SUPER_ADMIN', 1, NULL, 'SUPER_ADMIN', NULL, 'phamminhhiep0402@gmail.com', '$2a$10$D1hSbXw6gA.c9o8TIAZ/MOK3iROxxxU4ntBJBURkjwx0ELzQAQQ6m', '0912345667');

-- --------------------------------------------------------

--
-- Table structure for table `user_role`
--

CREATE TABLE `user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_role`
--

INSERT INTO `user_role` (`user_id`, `role_id`) VALUES
(1, 3),
(2, 1),
(3, 3),
(6, 2),
(7, 2),
(8, 3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `consultant`
--
ALTER TABLE `consultant`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKdsrednop3x8vllxyy3fwa4d5j` (`user_id`),
  ADD KEY `FK18ciw3slef0u8g2ekmkccgbqw` (`category_id`);

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKeuat1oase6eqv195jvb71a93s` (`user_id`);

--
-- Indexes for table `department`
--
ALTER TABLE `department`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `question`
--
ALTER TABLE `question`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKalynicnc685iarulr6dbykir1` (`consultant_id`),
  ADD KEY `FKdjpe5cu8exwvg7a2m24t301mp` (`customer_id`),
  ADD KEY `FKhvcgg6ambb42id4akcmv7hqj` (`department_id`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKofx66keruapi6vyqpv6f2or37` (`name`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`);

--
-- Indexes for table `user_role`
--
ALTER TABLE `user_role`
  ADD PRIMARY KEY (`user_id`,`role_id`),
  ADD KEY `FKt7e7djp752sqn6w22i6ocqy6q` (`role_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `consultant`
--
ALTER TABLE `consultant`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `customers`
--
ALTER TABLE `customers`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `department`
--
ALTER TABLE `department`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `question`
--
ALTER TABLE `question`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `consultant`
--
ALTER TABLE `consultant`
  ADD CONSTRAINT `FK18ciw3slef0u8g2ekmkccgbqw` FOREIGN KEY (`category_id`) REFERENCES `department` (`id`),
  ADD CONSTRAINT `FK6f3stauq08w4dtd0ueh6afwfi` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `customers`
--
ALTER TABLE `customers`
  ADD CONSTRAINT `FKrh1g1a20omjmn6kurd35o3eit` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `question`
--
ALTER TABLE `question`
  ADD CONSTRAINT `FKalynicnc685iarulr6dbykir1` FOREIGN KEY (`consultant_id`) REFERENCES `consultant` (`id`),
  ADD CONSTRAINT `FKdjpe5cu8exwvg7a2m24t301mp` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`),
  ADD CONSTRAINT `FKhvcgg6ambb42id4akcmv7hqj` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`);

--
-- Constraints for table `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `FKj345gk1bovqvfame88rcx7yyx` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKt7e7djp752sqn6w22i6ocqy6q` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
