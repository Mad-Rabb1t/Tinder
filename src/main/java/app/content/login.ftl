
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="img/favicon.ico">

    <title>Signin Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="css/style.css">
</head>

<body class="text-center">
    <form class="form-signin" method="post">
        <img class="mb-4" src="https://getbootstrap.com/assets/brand/bootstrap-solid.svg" alt="" width="72" height="72">
        <h1 class="h3 mb-3 font-weight-normal">${error}</h1>
        <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
        <label for="inputEmail" class="sr-only">Login</label>
        <input type="text" id="inputEmail" class="form-control" placeholder="Login" name="login"  autofocus>
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" id="inputPassword" class="form-control" placeholder="Password" name="password" >
        <button class="btn btn-lg btn-primary btn-block" type="submit" name="button" value="signin">Sign in</button>
        <button class="btn btn-lg btn-primary btn-block" type="submit" name="button" value="register">Register</button>
        <p class="mt-5 mb-3 text-muted">&copy; Tinder 2020</p>
    </form>
</body>
</html>