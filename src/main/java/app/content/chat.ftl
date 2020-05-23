<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="img/tinder_logo.jpg">

    <title>Chat</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="css/style.css">
</head>
<body>


<div class="container">
    <div class="row">
        <div class="chat-main col-6 offset-3">
            <div class="col-md-12 chat-header">
                <div class="row header-one text-white p-1">
                    <div class="col-md-6 name pl-3">
                        <h5 class="ml-1 mb-2"><i class="fa fa-comment" style="font-size: large"></i> ${user.name}</h5>
                    </div>
                    <div class="col-md-6 options text-right pr-0">
                        <form method="post">
                        <button class="btn btn-outline-danger" name="Action" value="_&_X Æ A-12_&_">
                        <i class="fa fa-times text-center pt-1"></i>
                        </button>
                        </form>
                    </div>
                </div>
                <div class="row header-two w-100">
                    <div class="col-md-6 options-left pl-1">
                        <i class="fa fa-video-camera mr-3"></i>
                    </div>
                    <div class="col-md-6 options-right text-right pr-2">
                        <form method="post">
                        <button class="btn btn-sm " name="Action" value="⟳"><i class="fa">⟳</i></button>
                        </form>
                    </div>
                </div>
            </div>
            <div class="chat-content">
                <div class="col-md-12 chats pt-3 pl-2 pr-3 pb-3">
                    <ul class="p-0">
                        <#list allMessages as message>
                        <#if user.id == message.to>
                        <li class="send-msg float-right mb-2">
                            <p class="pt-1 pb-1 pl-2 pr-2 m-0 rounded">
                                ${message.text}
                            </p>
                            <span class="receive-msg-time">${message.date}</span>
                        </li>
                        <#else>
                        <li class="receive-msg float-left mb-2">
                            <div class="sender-img">
                                <img src="${user.photo}" class="float-left">
                            </div>
                            <div class="receive-msg-desc float-left ml-2">
                                <p class="bg-white m-0 pt-1 pb-1 pl-2 pr-2 rounded">
                                    ${message.text}
                                </p>
                                <span class="receive-msg-time">${message.date}</span>
                            </div>
                        </li>
                        </#if>
                        </#list>
                    </ul>
                </div>
                <div class="col-md-12 p-2 msg-box border border-primary">
                    <div class="row">
                        <div class="col-md-2 options-left">
                            <i class="fa fa-smile-o"></i>
                        </div>
                        <div class="col-md-7 pl-0">
                            <form method="post">
                            <label>
                                <input readonly type="hidden" name="userId" value="${user.id}">
                                <input type="text" name="Action" class="border-0" placeholder="Send message" />
                            </label>
                                <button class="btn btn-sm btn-outline-success float-right " > Send </button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>