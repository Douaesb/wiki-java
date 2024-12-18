<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Author Management</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100">
<nav class="bg-slate-800 text-white shadow-lg">
    <div class="container mx-auto px-6 py-3 flex justify-between items-center">
        <div class="flex space-x-4">
            <a href="#" class="font-bold text-xl">MyBlog</a>
            <a href="#" class="hover:text-blue-200">Articles</a>
            <a href="#" class="hover:text-blue-200">Authors</a>
            <a href="#" class="hover:text-blue-200">Comments</a>
        </div>
        <div class="pr-4">
            <c:if test="${not empty sessionScope.adminEmail}">
                <form action="<%=request.getContextPath()%>/logout" method="post">
                    <button type="submit" class="bg-white text-black px-4 py-2 rounded hover:bg-gray-200">
                        Logout
                    </button>
                </form>
            </c:if>
        </div>
    </div>
</nav>

<main class="container mx-auto mt-8 px-4">
    <div class="bg-gray-100 p-4 rounded-md shadow-sm mb-4">
        <h2 class="text-lg font-semibold text-gray-700">Admin Information</h2>
        <p class="text-gray-600">
            Your email : <strong>${adminEmail}</strong>
        </p>
        <p class="text-gray-600">
            Your role: <strong>${adminRole}</strong>
        </p>
    </div>
    <h1 class="text-3xl font-bold mb-6 text-gray-800">Author List</h1>

    <div class="flex justify-end mb-4">
        <button onclick="document.getElementById('popupForm').classList.remove('hidden')"
                class="text-white bg-slate-800 hover:bg-slate-700 px-4 py-2 rounded-lg">
            Add Author
        </button>
    </div>

    <div id="popupForm" class="hidden fixed inset-0 bg-gray-900 bg-opacity-75 flex items-center justify-center z-50">
        <div class="bg-gray-100 rounded-lg shadow-lg w-full max-w-md relative">

            <jsp:include page="form-add-author.jsp" />
        </div>
    </div>

    <jsp:include page="show.jsp" />

</main>
</body>
</html>
