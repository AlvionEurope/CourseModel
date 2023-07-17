-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1:3306
-- Время создания: Июл 17 2023 г., 04:24
-- Версия сервера: 8.0.30
-- Версия PHP: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `spring_web`
--

-- --------------------------------------------------------

--
-- Структура таблицы `course`
--

CREATE TABLE `course` (
  `id` bigint NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `price` float NOT NULL,
  `professor_id` bigint DEFAULT NULL,
  `courses` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп данных таблицы `course`
--

INSERT INTO `course` (`id`, `name`, `price`, `professor_id`, `courses`) VALUES
(1, 'Advanced', 90000, 1, 1),
(2, 'Beginner', 45000, 1, 1);

-- --------------------------------------------------------

--
-- Структура таблицы `course_seq`
--

CREATE TABLE `course_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп данных таблицы `course_seq`
--

INSERT INTO `course_seq` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Структура таблицы `course_students`
--

CREATE TABLE `course_students` (
  `course_id` bigint NOT NULL,
  `students_num` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп данных таблицы `course_students`
--

INSERT INTO `course_students` (`course_id`, `students_num`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Структура таблицы `progress`
--

CREATE TABLE `progress` (
  `id` bigint NOT NULL,
  `mark` varbinary(255) DEFAULT NULL,
  `course_id` bigint DEFAULT NULL,
  `student_num` bigint DEFAULT NULL,
  `stud_marks` bigint DEFAULT NULL,
  `quantity` int NOT NULL,
  `sum` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп данных таблицы `progress`
--

INSERT INTO `progress` (`id`, `mark`, `course_id`, `student_num`, `stud_marks`, `quantity`, `sum`) VALUES
(1, 0x00, 1, 1, 1, 10, 1);

-- --------------------------------------------------------

--
-- Структура таблицы `progress_mark`
--

CREATE TABLE `progress_mark` (
  `progress_id` bigint NOT NULL,
  `mark` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп данных таблицы `progress_mark`
--

INSERT INTO `progress_mark` (`progress_id`, `mark`) VALUES
(1, 12),
(1, 45),
(1, 90),
(1, 90),
(1, 90),
(1, 90),
(1, 90),
(1, 90),
(1, 90),
(1, 90);

-- --------------------------------------------------------

--
-- Структура таблицы `progress_seq`
--

CREATE TABLE `progress_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп данных таблицы `progress_seq`
--

INSERT INTO `progress_seq` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Структура таблицы `student`
--

CREATE TABLE `student` (
  `num` bigint NOT NULL,
  `adress` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `avgmark` float NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `tel` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `stud_marks` bigint DEFAULT NULL,
  `students` bigint DEFAULT NULL,
  `fin_courses` varbinary(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп данных таблицы `student`
--

INSERT INTO `student` (`num`, `adress`, `avgmark`, `email`, `name`, `tel`, `stud_marks`, `students`, `fin_courses`) VALUES
(1, 'Adress', 0, 'mail', 'name', '+7978', 1, 1, NULL);

-- --------------------------------------------------------

--
-- Структура таблицы `student_seq`
--

CREATE TABLE `student_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп данных таблицы `student_seq`
--

INSERT INTO `student_seq` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Структура таблицы `teacher`
--

CREATE TABLE `teacher` (
  `id` bigint NOT NULL,
  `adress` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `pay` float NOT NULL,
  `tel` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп данных таблицы `teacher`
--

INSERT INTO `teacher` (`id`, `adress`, `name`, `pay`, `tel`) VALUES
(1, 'Севастополь', 'Волкова', 45000, '+7978'),
(2, 'Игиф', 'Алькова', 35000, '+7978');

-- --------------------------------------------------------

--
-- Структура таблицы `teacher_course`
--

CREATE TABLE `teacher_course` (
  `teacher_id` bigint NOT NULL,
  `course_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп данных таблицы `teacher_course`
--

INSERT INTO `teacher_course` (`teacher_id`, `course_id`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Структура таблицы `teacher_seq`
--

CREATE TABLE `teacher_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп данных таблицы `teacher_seq`
--

INSERT INTO `teacher_seq` (`next_val`) VALUES
(1);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKjx64h1v83gvpp22imdwkv45i9` (`professor_id`),
  ADD KEY `FKsd8uljyhh955q60nck78tq4yn` (`courses`);

--
-- Индексы таблицы `course_students`
--
ALTER TABLE `course_students`
  ADD KEY `FK29g6uw2p02164as9klv8rmg7a` (`students_num`),
  ADD KEY `FKgut5xj4l8sk6hg3l0t2su2pnc` (`course_id`);

--
-- Индексы таблицы `progress`
--
ALTER TABLE `progress`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_4m777qjgiq4vkeiykfbh1dw9i` (`course_id`),
  ADD UNIQUE KEY `UK_ph41qfkucfqsyr6i4821hn3jl` (`student_num`),
  ADD KEY `FK9081pck4pjpg8dp99s6crbsop` (`stud_marks`);

--
-- Индексы таблицы `progress_mark`
--
ALTER TABLE `progress_mark`
  ADD KEY `FKflrsraism4iylguw9s0pgx6u5` (`progress_id`);

--
-- Индексы таблицы `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`num`),
  ADD UNIQUE KEY `UK_snxwmw8dtmv6g9evndvg3hbx5` (`stud_marks`),
  ADD KEY `FKosk4vu36g15owb9efsek37h7m` (`students`);

--
-- Индексы таблицы `teacher`
--
ALTER TABLE `teacher`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `teacher_course`
--
ALTER TABLE `teacher_course`
  ADD PRIMARY KEY (`teacher_id`,`course_id`),
  ADD UNIQUE KEY `UK_7ek9jh4evh1i4kjakmoh3td2j` (`course_id`);

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `course`
--
ALTER TABLE `course`
  ADD CONSTRAINT `FKjx64h1v83gvpp22imdwkv45i9` FOREIGN KEY (`professor_id`) REFERENCES `teacher` (`id`),
  ADD CONSTRAINT `FKsd8uljyhh955q60nck78tq4yn` FOREIGN KEY (`courses`) REFERENCES `teacher` (`id`);

--
-- Ограничения внешнего ключа таблицы `course_students`
--
ALTER TABLE `course_students`
  ADD CONSTRAINT `FK29g6uw2p02164as9klv8rmg7a` FOREIGN KEY (`students_num`) REFERENCES `student` (`num`),
  ADD CONSTRAINT `FKgut5xj4l8sk6hg3l0t2su2pnc` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`);

--
-- Ограничения внешнего ключа таблицы `progress`
--
ALTER TABLE `progress`
  ADD CONSTRAINT `FK9081pck4pjpg8dp99s6crbsop` FOREIGN KEY (`stud_marks`) REFERENCES `student` (`num`),
  ADD CONSTRAINT `FKswuei50cdlq9o0qfkpha3ivks` FOREIGN KEY (`student_num`) REFERENCES `student` (`num`),
  ADD CONSTRAINT `FKsyhehp7u3ky9phqokro91ih4v` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`);

--
-- Ограничения внешнего ключа таблицы `progress_mark`
--
ALTER TABLE `progress_mark`
  ADD CONSTRAINT `FKflrsraism4iylguw9s0pgx6u5` FOREIGN KEY (`progress_id`) REFERENCES `progress` (`id`);

--
-- Ограничения внешнего ключа таблицы `student`
--
ALTER TABLE `student`
  ADD CONSTRAINT `FKgk82bs1x85aeeb8aldlbwoklc` FOREIGN KEY (`stud_marks`) REFERENCES `progress` (`id`),
  ADD CONSTRAINT `FKosk4vu36g15owb9efsek37h7m` FOREIGN KEY (`students`) REFERENCES `course` (`id`);

--
-- Ограничения внешнего ключа таблицы `teacher_course`
--
ALTER TABLE `teacher_course`
  ADD CONSTRAINT `FKaleldsg7yww5as540ld8iwghe` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`),
  ADD CONSTRAINT `FKp8bco6842vkqh13y4759ib7tk` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
