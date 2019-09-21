<%@include file="../includes/sessaoPaginaExterna.jsp" %> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Minhas Finanças - Bem-vindo!</title>

  
    <!-- Bootstrap Core CSS -->
        <link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- MetisMenu CSS -->
        <link href="../vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

        <!-- Custom CSS -->
        <link href="../dist/css/sb-admin-2.css" rel="stylesheet">

        <!-- Morris Charts CSS -->
        <link href="../vendor/morrisjs/morris.css" rel="stylesheet">

        <!-- Custom Fonts -->
        <link href="../vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- Custom CSS -->
    <link href="../dist/css/full-slider.css" rel="stylesheet">

  </head>

<body>
    <nav class="navbar navbar-default navbar-fixed-top">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbarCel" aria-expanded="false">
                            <span class="sr-only"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span> <!-- Barrinhas Que aparecem quando diminui o tamanho da tela -->
                        </button>

                        <a class="navbar-brand" href="#"><img src="../img/money-logo.png" width="50px" ></img></a>
                        <a class="navbar-brand" href="#">Minhas Finanças</a>
                    </div>

                    <div class="collapse navbar-collapse" id="navbarCel">
                        <ul class="nav navbar-nav navbar-right" role="form">
                            <li>
                                <form class="navbar-form navbar-right" action="painel.html"> 
                                    <button type="button" onclick="window.location.href = 'cadastro_usuario.jsp'" class="btn btn-info" id="btnCadastrar">Cadastrar-se</button>
                                    <button type="button" onclick="window.location.href = 'login.jsp'" class="btn btn-primary" id="btnEntrar">Entrar</button>
                                </form>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>

    <!-- Full Page Image Background Carousel Header -->
    <header id="myCarousel" class="carousel slide">
        <!-- Indicators -->
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
            <li data-target="#myCarousel" data-slide-to="2"></li>
        </ol>

        <!-- Wrapper for Slides -->
        <div class="carousel-inner">
            <div class="item active">
                <!-- Set the first background image using inline CSS below. -->
                <div class="fill" style="background-image:url('../img/money1.jpg');"></div>
                <div class="carousel-caption">
                    <h2>Planeje seus gastos</h2>
                </div>
            </div>
            <div class="item">
                <!-- Set the second background image using inline CSS below. -->
                <div class="fill" style="background-image:url('../img/money2.jpg');"></div>
                <div class="carousel-caption">
                    <h2>Acompanhe suas finanças</h2>
                </div>
            </div>
            <div class="item">
                <!-- Set the third background image using inline CSS below. -->
                <div class="fill" style="background-image:url('../img/money3.jpg');"></div>
                <div class="carousel-caption">
                    <h2>Poupe dinheiro</h2>
                </div>
            </div>
        </div>

        <!-- Controls -->
        <a class="left carousel-control" href="#myCarousel" data-slide="prev">
            <span class="icon-prev"></span>
        </a>
        <a class="right carousel-control" href="#myCarousel" data-slide="next">
            <span class="icon-next"></span>
        </a>

    </header>

    <!-- Page Content -->
    <div class="container">

   

        <hr>

        <!-- Footer -->
        <footer>
            <div class="row">
                <div class="col-lg-12">
                    <p>Copyright &copy; Minhas Finanças 2016</p>
                    <p>Projeto da Disciplina de Desenvolvimento de Sistemas WEB II</p>
                </div>
            </div>
            <!-- /.row -->
        </footer>

    </div>
    <!-- /.container -->

    <!-- jQuery -->
    <script src="../vendor/jquery/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="../vendor/bootstrap/js/bootstrap.min.js"></script>


</body>

</html>
