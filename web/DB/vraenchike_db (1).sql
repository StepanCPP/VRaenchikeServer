-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Хост: 127.0.0.1
-- Время создания: Апр 15 2015 г., 20:26
-- Версия сервера: 5.5.25
-- Версия PHP: 5.3.13

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- База данных: `vraenchike_db`
--

-- --------------------------------------------------------

--
-- Структура таблицы `banned`
--

CREATE TABLE IF NOT EXISTS `banned` (
  `idBanned` int(11) NOT NULL AUTO_INCREMENT,
  `link` varchar(145) DEFAULT NULL,
  PRIMARY KEY (`idBanned`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Структура таблицы `photo`
--

CREATE TABLE IF NOT EXISTS `photo` (
  `idPhoto` int(11) NOT NULL AUTO_INCREMENT,
  `URL` varchar(150) DEFAULT NULL,
  `Likes` int(11) DEFAULT NULL,
  `Dislike` int(11) DEFAULT NULL,
  PRIMARY KEY (`idPhoto`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=17 ;

--
-- Дамп данных таблицы `photo`
--

INSERT INTO `photo` (`idPhoto`, `URL`, `Likes`, `Dislike`) VALUES
(2, 'sds', 0, 0),
(3, 'sds', 0, 0),
(5, 'Ð²ÑÐ²Ð²ÑÐ°ÑÐ¿ÑÑÐ¿Ð°Ñ', 0, 0),
(6, 'sds', 0, 0),
(7, 'dssrfsafas', 0, 0),
(8, 'ddsdsadsadasdsq', 0, 0),
(9, 'welcome', 0, 0);

-- --------------------------------------------------------

--
-- Структура таблицы `place`
--

CREATE TABLE IF NOT EXISTS `place` (
  `idPlace` int(11) NOT NULL AUTO_INCREMENT,
  `lng` double DEFAULT NULL,
  `lat` double DEFAULT NULL,
  `radius` int(11) DEFAULT NULL,
  PRIMARY KEY (`idPlace`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=7 ;

--
-- Дамп данных таблицы `place`
--

INSERT INTO `place` (`idPlace`, `lng`, `lat`, `radius`) VALUES
(6, 0, 122, 0);

-- --------------------------------------------------------

--
-- Структура таблицы `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `idUser` int(11) NOT NULL AUTO_INCREMENT,
  `lastPhotoView` datetime DEFAULT NULL,
  `user_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idUser`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- Дамп данных таблицы `user`
--

INSERT INTO `user` (`idUser`, `lastPhotoView`, `user_name`) VALUES
(5, '2015-04-15 12:22:22', 'ddsdsadsadasdsq');

-- --------------------------------------------------------

--
-- Структура таблицы `userbanned`
--

CREATE TABLE IF NOT EXISTS `userbanned` (
  `idUserBanned` int(11) NOT NULL AUTO_INCREMENT,
  `idUser` int(11) NOT NULL,
  `idBanned` int(11) NOT NULL,
  `User_idUser` int(11) NOT NULL,
  `Banned_idBanned` int(11) NOT NULL,
  PRIMARY KEY (`idUserBanned`),
  KEY `fk_UserBanned_User1_idx` (`User_idUser`),
  KEY `fk_UserBanned_Banned1_idx` (`Banned_idBanned`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Структура таблицы `userlogininfo`
--

CREATE TABLE IF NOT EXISTS `userlogininfo` (
  `idUserLoginInfo` int(11) NOT NULL AUTO_INCREMENT,
  `idUser` int(11) NOT NULL,
  `login` varchar(120) DEFAULT NULL,
  `pass` varchar(120) DEFAULT NULL,
  `User_idUser` int(11) NOT NULL,
  PRIMARY KEY (`idUserLoginInfo`),
  KEY `fk_UserLoginInfo_User1_idx` (`User_idUser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Структура таблицы `userphoto`
--

CREATE TABLE IF NOT EXISTS `userphoto` (
  `idUserPhoto` int(11) NOT NULL AUTO_INCREMENT,
  `idUser` int(11) NOT NULL,
  `idPhoto` int(11) NOT NULL,
  `Photo_idPhoto` int(11) NOT NULL,
  `User_idUser` int(11) NOT NULL,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`idUserPhoto`,`Photo_idPhoto`),
  KEY `fk_UserPhoto_Photo1_idx` (`Photo_idPhoto`),
  KEY `fk_UserPhoto_User1_idx` (`User_idUser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Структура таблицы `userplaces`
--

CREATE TABLE IF NOT EXISTS `userplaces` (
  `idUserPlaces` int(11) NOT NULL AUTO_INCREMENT,
  `idUser` int(11) NOT NULL,
  `idPlace` varchar(45) DEFAULT NULL,
  `User_idUser` int(11) NOT NULL,
  `Place_idPlace` int(11) NOT NULL,
  PRIMARY KEY (`idUserPlaces`,`User_idUser`,`Place_idPlace`),
  KEY `fk_UserPlaces_User_idx` (`User_idUser`),
  KEY `fk_UserPlaces_Place1_idx` (`Place_idPlace`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `userbanned`
--
ALTER TABLE `userbanned`
  ADD CONSTRAINT `fk_UserBanned_Banned1` FOREIGN KEY (`Banned_idBanned`) REFERENCES `banned` (`idBanned`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_UserBanned_User1` FOREIGN KEY (`User_idUser`) REFERENCES `user` (`idUser`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Ограничения внешнего ключа таблицы `userlogininfo`
--
ALTER TABLE `userlogininfo`
  ADD CONSTRAINT `fk_UserLoginInfo_User1` FOREIGN KEY (`User_idUser`) REFERENCES `user` (`idUser`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Ограничения внешнего ключа таблицы `userphoto`
--
ALTER TABLE `userphoto`
  ADD CONSTRAINT `fk_UserPhoto_Photo1` FOREIGN KEY (`Photo_idPhoto`) REFERENCES `photo` (`idPhoto`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_UserPhoto_User1` FOREIGN KEY (`User_idUser`) REFERENCES `user` (`idUser`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Ограничения внешнего ключа таблицы `userplaces`
--
ALTER TABLE `userplaces`
  ADD CONSTRAINT `fk_UserPlaces_Place1` FOREIGN KEY (`Place_idPlace`) REFERENCES `place` (`idPlace`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_UserPlaces_User` FOREIGN KEY (`User_idUser`) REFERENCES `user` (`idUser`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
