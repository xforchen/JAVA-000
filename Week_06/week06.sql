 C R E A T E   T A B L E  users
 ( 
    user I d             c h a r ( 1 0 )     N O T   N U L L  , 
     n a m e         c h a r ( 5 0 )     N O T   N U L L  , 
     a d d r e s s   c h a r ( 5 0 )     N U L L  , 
     c i t y         c h a r ( 5 0 )     N U L L  , 
     s t a t e       c h a r ( 5 )       N U L L  , 
     z i p           c h a r ( 1 0 )     N U L L  , 
     c o u n t r y   c h a r ( 5 0 )     N U L L  , 
      c o n t a c t   c h a r ( 5 0 )     N U L L  , 
     e m a i l       c h a r ( 2 5 5 )   N U L L ,
  PRIMARY KEY (`user I d `)
 ) ENGINE=InnoDB  DEFAULT CHARSET=utf8; 

 C R E A T E   T A B L E   p r o d u c t s 
 ( 
     p r o d  I d         c h a r ( 1 0 )             N O T   N U L L ,
     n a m e     c h a r ( 2 5 5 )           N O T   N U L L , 
     p r i c e   d e c i m a l ( 8 , 2 )     N O T   N U L L , 
     d e s c     t e x t                     N U L L ,
  PRIMARY KEY (` p r o d  I d `)
 ) ENGINE=InnoDB  DEFAULT CHARSET=utf8; 

 C R E A T E   T A B L E   o r d e r s 
 ( 
  orderId   char(10) NOT NULL,
      n u m     i n t             N O T   N U L L  , 
      d a t e   d a t e t i m e   N O T   N U L L , 
    user I d         c h a r ( 1 0 )   N O T   N U L L ,
  PRIMARY KEY (`orderId`)
 ) ENGINE=InnoDB  DEFAULT CHARSET=utf8 ; 

 C R E A T E   T A B L E   o r d e r I t e m s 
 ( 
   or d e r I t e mId char(10) NOT NULL,
    orderId   char(10) NOT NULL,
     p r o d  I d         c h a r ( 1 0 )           N O T   N U L L  , 
     q u a n t i t y       i n t                     N O T   N U L L  , 
      p r i c e   d e c i m a l ( 8 , 2 )   N O T   N U L L,
   PRIMARY KEY (` or d e r I t e mId`)
 )  ENGINE=InnoDB  DEFAULT CHARSET=utf8 ; 
