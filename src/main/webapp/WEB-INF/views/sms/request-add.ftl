<!doctype html>
<!--[if lt IE 7]> <html class="no-js ie6 oldie" lang="en"> <![endif]-->
<!--[if IE 7]>    <html class="no-js ie7 oldie" lang="en"> <![endif]-->
<!--[if IE 8]>    <html class="no-js ie8 oldie" lang="en"> <![endif]-->
<!--[if IE 9]>    <html class="no-js ie9" lang="en"> <![endif]-->
<!--[if gt IE 9]><!--> <html> <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
    <meta name="description" content="Flatfy Free Flat and Responsive HTML5 Template ">
    <meta name="author" content="">

<#include "header.ftl">
    <title>Flatfy – Free Flat and Responsive HTML5 Template</title>

    <!-- Bootstrap core CSS -->
    <link href="/resources/css/bootstrap-template.min.css" rel="stylesheet">
 
    <!-- Custom Google Web Font -->
    <link href="/resources/css/font-awesome.min.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Lato:100,300,400,700,900,100italic,300italic,400italic,700italic,900italic' rel='stylesheet' type='text/css'>
	<link href='http://fonts.googleapis.com/css?family=Arvo:400,700' rel='stylesheet' type='text/css'>
	 <link rel="stylesheet" href="/resources/css/unpkg-pure-min.css">
    <link rel="stylesheet" href="/resources/css/side-menu.css"></head>
    <!-- Custom CSS-->
    <link href="/resources/css/general.css" rel="stylesheet">
	
	 <!-- Owl-Carousel -->
    <link href="/resources/css/custom.css" rel="stylesheet">
	<link href="/resources/css/owl.carousel.css" rel="stylesheet">
    <link href="/resources/css/owl.theme.css" rel="stylesheet">
	<link href="/resources/css/style-template.css" rel="stylesheet">
	<link href="/resources/css/animate.css" rel="stylesheet">
	
	<!-- Magnific Popup core CSS file -->
	<link rel="stylesheet" href="/resources/css/magnific-popup.css"> 
	
	<script src="/resources/js/modernizr-2.8.3.min.js"></script>  <!-- Modernizr /-->
	<!--[if IE 9]>
		<script src="/resources/js/PIE_IE9.js"></script>
	<![endif]-->
	<!--[if lt IE 9]>
		<script src="/resources/js/PIE_IE678.js"></script>
	<![endif]-->

	<!--[if lt IE 9]>
		<script src="/resources/js/html5shiv.js"></script>
	<![endif]-->

</head>

<body id="home">

	<!-- Preloader -->
	<div id="preloader">
		<div id="status"></div>
	</div>
	
	<!-- FullScreen -->
    <div class="intro-header">
		<div class="col-xs-12 text-center abcen1">
			<div id="layout">
			<#include "menu.ftl">
			    <div class="main" style=" max-width:720px;">
    			<#include "add-configuration.ftl">
			    <#include "response.ftl">
			    </div>
			</div>
		</div>    
    </div>

    <!-- JavaScript -->
    <script src="/resources/js/jquery-1.10.2.js"></script>
    <script src="/resources/js/bootstrap.js"></script>
	<script src="/resources/js/owl.carousel.js"></script>
	<script src="/resources/js/script.js"></script>
	<!-- StikyMenu -->
	<script src="/resources/js/stickUp.min.js"></script>
	<script type="text/javascript">
	  jQuery(function($) {
		$(document).ready( function() {
		  $('.navbar-default').stickUp();
		  
		});
	  });
	
	</script>
	<!-- Smoothscroll -->
	<script type="text/javascript" src="/resources/js/jquery.corner.js"></script> 
	<script src="/resources/js/wow.min.js"></script>
	<script>
	 new WOW().init();
	</script>
	<script src="/resources/js/classie.js"></script>
	<script src="/resources/js/uiMorphingButton_inflow.js"></script>
	<script type="text/javascript" src="/resources/js/tools.js"></script>
	
	<!-- Magnific Popup core JS file -->
	<script src="/resources/js/jquery.magnific-popup.js"></script> 
</body>

</html>