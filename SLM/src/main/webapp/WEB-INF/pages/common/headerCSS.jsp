<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/includes.jspf"%>

<%-- CSS --%>
    <!-- import CSS here -->
<%-- /CSS --%>

<style type="text/css">

*{position:relative;-webkit-box-sizing: border-box;-moz-box-sizing: border-box;box-sizing: border-box; margin: 0; padding: 0;}
html { height: 100%; }
body { height: 100%; margin: 0; padding: 0; background-color: #f2f2f2;}
#global-wrapper{width:100%; margin: 0; padding: 0;}
#headband{background-color: #953735; color: white;   width:100%;  padding: 5px 15px;} 
#headband a,#menu a{color: white; text-decoration:none;}
#menu ul{background-color: #18385f; color: white;}/*10253f*/
header div#menu-wrapper{font-size:1.3em;}
header div#menu-wrapper li{display:inline-block; margin: 0; padding: 0;height:40px;}
header div#menu-wrapper li a{display:inline-block; width:100%; border-top:1px solid #275ea0;border-left:1px solid #275ea0;border-bottom:1px solid #050c14; border-right:1px solid #050c14; height:40px; line-height:38px;}
header div#menu-wrapper li a:hover{background-color: #1d4474;border-bottom:1px solid #275ea0;border-right:1px solid #275ea0;border-top:1px solid #050c14;border-left:1px solid #050c14;}/*  */

#content-wrapper{background-color: #ecebff; color: black;}

#content-wrapper>div {padding: 5px 5px 0px 5px;}
#content-wrapper>div.no-marge{padding:0;margin:0;}
.panel{background-color: white; color: black;border: 1px solid #d9d9d9; width:100%; padding: 5px; margin-bottom: 20px;}

p{padding:0;margin:0;}
w100{width:100%;}
no-marge{padding:0;margin:0;}

.float-right{float:right;}
.float-left{float:left;}
.clear-leaft{float:left;}
.clear-right{clear:right;}
.clear-both{clear:both;}
.ml5{margin-left:5px;}
.mr5{margin-right:5px;}
.ml10{margin-left:10px;}
.mr10{margin-right:10px;}
.ml15{margin-left:15px;}
.mr15{margin-right:15px;}
.ml20{margin-left:20px;}
.mr20{margin-right:20px;}
.pl5{padding-left:5px;}
.pr5{padding-right:5px;}
.pl10{padding-left:10px;}
.pr10{padding-right:10px;}
.pl15{padding-left:15px;}
.pr15{padding-right:15px;}
.pl20{padding-left:20px;}
.pr20{padding-right:20px;}
        
/* Desktop display */
@media only screen and (min-width: 1025px) {
    #headband{min-width:1000px; height: 33px;}
    #headband div.float-right{margin-left: 10px; }
    #headband div, #headband nav, #headband img, #headband ul, #headband li, #headband a{display:inline; height: 20px; margin: 0; padding: 0; line-height:20px;}          
    #headband div#newEvent-wrapper{width:55%; text-align:center;}
    #headband div#search-wrapper{margin-left:40px;}

    #wrapper{min-width:900px; width:90%; margin: 0pt 5%; }

    header {width:100%; height: 40px; display:block; margin-top: 50px;}

    header div#logo-wrapper{ float:left; height:100%;}
    header a#logo {width:220px; height:100%;}
    header a#logo, header a#logo img {bottom: -7px;left: 0;position: absolute; z-index:11;}
    header div#logo-wrapper div#logo-rel{position: relative; height:100%; width:100%; }

    header div#menu-wrapper{margin-left:206px;}
    header div#menu-wrapper ul{display:inline-block; margin: 0; padding: 0; height:40px; line-height:38px;}
    header div#menu-wrapper ul{width:100%;}
    header div#menu-wrapper li{width:16.666666%; text-align:center; float:left;} /*border-left: 1px solid #F2F2F2;*/
    header nav#menu {width:100%; height: 40px;}
    header a#logo,header nav#menu {}

    #content-wrapper{width:100%; min-height: 200px; padding: 30px 10px; box-shadow: 0 2px 7px #555555; -webkit-box-shadow: 1px 2px 7px #555555;}
    
    
    /* main side 80 (2) 18 */
    .right-main-1{float:right; width:80%; }
    .left-side-1{width:18%;}
    
    .left-main-1{float:left; width:80%; }
    .right-side-1{width:18%;}
    
    /* main side 85 (2) 13 */
    .right-main-2{float:right; width:85%; }
    .left-side-2{width:13%;}
    
    .left-main-2{float:left; width:85%; }
    .right-side-2{width:13%;}
    
    /* main side 68 (2) 30 */
    .right-main-3{float:right; width:68%; }
    .left-side-3{width:30%;}
    
    .left-main-3{float:left; width:68%; }
    .right-side-3{width:30%;}
    
    /* main main 49 (2) 19 */
    .two-colomn-right{width:49%; float:right;}
    .two-colomn-left{width:49%; float:left;}
    
    .panel.round {-webkit-border-radius: 6px;-moz-border-radius: 6px;border-radius: 6px;}
    .panel.round2 {-webkit-border-radius: 8px;-moz-border-radius: 8px;border-radius: 8px;}
    .panel.round3 {-webkit-border-radius: 4px;-moz-border-radius: 4px;border-radius: 4px;}
}

/* commun def for non-large-desktop viw */
@media only screen and (max-width : 1024px)
 {
    #headband{display: none;}
    #logo-wrapper *, header {}
    #menu-wrapper{}
    #logo-wrapper, #menu-wrapper{}/* float:left;*/
    #menu{padding:5px;}
    header ul, header li{margin:0;padding:0;display:block;}
    header ul {width: 100%; overflow:hidden;}
    header ul {width: 100%; overflow:hidden;}
    header li{float:left; text-align:center; }
}

/* Tablet landscape and small desktop view */
@media only screen and (min-width : 769px) and (max-width : 1024px){
    header li{width:16.66666666%;/* 6 per line => 1 line */}
}

/* Tablet portrait and smartphone landscape view */
@media only screen and (min-width : 481px) and (max-width : 769px) {
    header li{width:33.333333%;/* 3 per line => 2 lines */}
}

/* Smartphone portrait and small landscape view */
@media only screen and (min-width : 321px) and (max-width : 480px) {
    header li{width:50%;/* 2 per line => 3 lines */}
}

/* Small Smartphone protrait view */
@media only screen and (max-width : 320px) {
    header li{width:100%;/* 1 per line => 6 lines */}
}

</style>