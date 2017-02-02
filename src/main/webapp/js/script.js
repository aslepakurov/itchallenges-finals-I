$(function () {
    console.log("Initialized...");
    $("#createUser").on("click", function () {
        var userName = $("#username").val();
        var password = $("#password").val();
        if (userName == "" || password == "") {
            $("#submitUser").addClass("text-danger");
            $("#submitUser").text("Username or password missing");
        } else {
            var json = "{\"name\":\"" + userName + "\", \"password\":\"" + password + "\"}"
            console.log(json);
            $.ajax({
                method: "POST",
                url: "http://localhost:9999/rest/user/create",
                data: json,
                contentType: "application/json",
                accept: "application/json",
                mimeType: "application/json",
                dataType: "json"
            }).success(function (message) {
                $("#submitUser").text("User id = " + message.responseText);
            }).error(function (message) {
                $("#submitUser").text(message.responseText);
            })
        }
    });

    $("#loginUser").on("click", function () {
        var userName = $("#username").val();
        var password = $("#password").val();
        if (userName == "" || password == "") {
            $("#submitUser").addClass("text-danger");
            $("#submitUser").text("Username or password missing");
        } else {
            var json = "{\"name\":\"" + userName + "\", \"password\":\"" + password + "\"}"
            console.log(json);
            $.ajax({
                method: "POST",
                url: "http://localhost:9999/rest/user/login",
                data: json,
                contentType: "application/json",
                accept: "application/json",
                mimeType: "application/json",
                dataType: "json"
            }).success(function (message) {
                $("#submitUser").text("Token id = " + message.responseText);
            }).error(function (message) {
                $("#submitUser").text(message.responseText);
            })
        }
    });


    $("#userNameBtn").on("click", function () {
        var id = $("#id").val();
        if (id == "") {
            $("#userName").addClass("text-danger");
            $("#userName").text("Id missing");
        } else {
            $.ajax({
                method: "GET",
                url: "http://localhost:9999/rest/user/getUserById?userId="+id,
                contentType: "application/json",
                accept: "application/json",
                mimeType: "application/json",
                dataType: "json"
            }).success(function (message) {
                $("#userName").text("User name = " + message.responseText);
            }).error(function (message) {
                $("#userName").text(message.responseText);
            })
        }
    });

    $("#logout").on("click", function () {
        var id = $("#id").val();
        if (id == "") {
            $("#logoutText").addClass("text-danger");
            $("#logoutText").text("Id missing");
        } else {
            $.ajax({
                method: "GET",
                url: "http://localhost:9999/rest/user/logout?tokenId="+id,
                contentType: "application/json",
                accept: "application/json",
                mimeType: "application/json",
                dataType: "json"
            }).success(function (message) {
                $("#logoutText").text(message.responseText);
            }).error(function (message) {
                $("#logoutText").text(message.responseText);
            })
        }
    });

    $("#followBtn").on("click", function () {
        var tokenId = $("#tokenId").val();
        var userId = $("#userId").val();
        if (tokenId == "" || userId == "") {
            $("#followText").addClass("text-danger");
            $("#followText").text("TokenId or userId missing");
        } else {
            var json = "{\"tokenId\":\"" + tokenId + "\", \"followeeId\":\"" + userId + "\"}"
            console.log(json);
            $.ajax({
                method: "POST",
                url: "http://localhost:9999/rest/user/follow",
                data: json,
                contentType: "application/json",
                accept: "application/json",
                mimeType: "application/json",
                dataType: "json"
            }).success(function (message) {
                $("#followText").text(message.responseText);
            }).error(function (message) {
                $("#followText").text(message.responseText);
            })
        }
    });

    $("#TextBtn").on("click", function () {
        var tokenId = $("#tokenId").val();
        var text = $("#text").val();
        if (tokenId == "" || text == "") {
            $("#Message").addClass("text-danger");
            $("#Message").text("TokenId or text missing");
        } else {
            var json = "{\"tokenId\":\"" + tokenId + "\", \"text\":\"" + text + "\"}"
            console.log(json);
            $.ajax({
                method: "POST",
                url: "http://localhost:9999/rest/chirik/create",
                data: json,
                contentType: "application/json",
                accept: "application/json",
                mimeType: "application/json",
                dataType: "json"
            }).success(function (message) {
                $("#Message").text(message.responseText);
            }).error(function (message) {
                $("#Message").text(message.responseText);
            })
        }
    });

    $("#myBtn").on("click", function () {
        var id = $("#tokenId").val();
        if (id == "") {
            $("#text").addClass("text-danger");
            $("#text").text("Id missing");
        } else {
            $.ajax({
                method: "GET",
                url: "http://localhost:9999/rest/chirik/my?tokenId="+id,
                contentType: "application/json",
                accept: "application/json",
                mimeType: "application/json",
                dataType: "json"
            }).success(function (message) {
                $("#text").text(JSON.stringify(message));
            }).error(function (message) {
                $("#text").text(JSON.stringify(message));
            })
        }
    });

    $("#followBtn").on("click", function () {
        var id = $("#tokenId").val();
        if (id == "") {
            $("#text").addClass("text-danger");
            $("#text").text("Id missing");
        } else {
            $.ajax({
                method: "GET",
                url: "http://localhost:9999/rest/chirik/following?tokenId="+id,
                contentType: "application/json",
                accept: "application/json",
                mimeType: "application/json",
                dataType: "json"
            }).success(function (message) {
                $("#text").text(JSON.stringify(message));
            }).error(function (message) {
                $("#text").text(JSON.stringify(message));
            })
        }
    });

    $("#mentionIdBtn").on("click", function () {
        var id = $("#tokenId").val();
        if (id == "") {
            $("#text").addClass("text-danger");
            $("#text").text("Id missing");
        } else {
            $.ajax({
                method: "GET",
                url: "http://localhost:9999/rest/chirik/mention?tokenId="+id,
                contentType: "application/json",
                accept: "application/json",
                mimeType: "application/json",
                dataType: "json"
            }).success(function (message) {
                $("#text").text(JSON.stringify(message));
            }).error(function (message) {
                $("#text").text(JSON.stringify(message));
            })
        }
    });

    $("#mentionBtn").on("click", function () {
        var username = $("#username").val();
        if (username == "") {
            $("#text").addClass("text-danger");
            $("#text").text("Username missing");
        } else {
            $.ajax({
                method: "GET",
                url: "http://localhost:9999/rest/chirik/mentionByUser?userName="+username,
                contentType: "application/json",
                accept: "application/json",
                mimeType: "application/json",
                dataType: "json"
            }).success(function (message) {
                $("#text").text(JSON.stringify(message));
            }).error(function (message) {
                $("#text").text(JSON.stringify(message));
            })
        }
    });

    $("#wallBtn").on("click", function () {
        var id = $("#tokenId").val();
        if (id == "") {
            $("#text").addClass("text-danger");
            $("#text").text("Id missing");
        } else {
            $.ajax({
                method: "GET",
                url: "http://localhost:9999/rest/chirik/wall?tokenId="+id,
                contentType: "application/json",
                accept: "application/json",
                mimeType: "application/json",
                dataType: "json"
            }).success(function (message) {
                $("#text").text(JSON.stringify(message));
            }).error(function (message) {
                $("#text").text(JSON.stringify(message));
            })
        }
    });
});