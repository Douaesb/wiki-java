<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Articles</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100">

<!-- Navbar -->
<header class="bg-gray-200 py-4 shadow-md">
    <div class="container mx-auto flex justify-between items-center">
        <!-- Logo -->
        <div class="text-5xl font-serif text-black pl-4">WIKI</div>

        <!-- Navbar Links -->
        <nav class="flex-grow flex justify-center space-x-12 text-lg">
            <a href="<%=request.getContextPath()%>/articles" class="text-gray-600 hover:text-gray-800">Articles</a>

            <!-- Check if the author's role is EDITOR -->
            <c:if test="${sessionScope.authorRole == 'EDITOR'}">
                <a href="<%=request.getContextPath()%>/articles?action=malist" class="text-gray-600 hover:text-gray-800">Mes Articles</a>
            </c:if>
        </nav>

        <div class="pr-4">
            <c:if test="${not empty sessionScope.authorName}">
                <form action="<%=request.getContextPath()%>/logout" method="post">
                    <button type="submit" class="bg-black text-white px-4 py-2 rounded hover:bg-gray-800">
                        Logout
                    </button>
                </form>
            </c:if>
        </div>
    </div>
</header>

<!-- Search Bar Centered Below the Navbar -->
<div class="mt-4 flex justify-center">
    <form action="<%=request.getContextPath()%>/articles?action=search" method="get" class="relative w-full md:w-1/2">
        <input type="text" name="query" placeholder="Search" required class="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
        <input type="hidden" name="action" value="search">
        <button type="submit" class="absolute inset-y-0 right-0 px-4 py-2 bg-black text-white rounded-r-md hover:bg-gray-800">Search</button>
    </form>
</div>

<!-- Main Content -->
<main class="max-w-7xl mx-auto mt-8 px-4">
    <div class="flex justify-between items-center mb-4">
        <h2 class="text-3xl font-semibold text-gray-800">Articles</h2>
        <button onclick="openModal()" class="bg-black text-white px-4 py-2 rounded hover:bg-gray-800">+ Add Article</button>
    </div>

    <!-- Article Cards Grid -->
    <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        <c:forEach var="article" items="${articles}">
            <div class="bg-white p-4 rounded shadow-md border">
                <div class="h-48 bg-gray-300 overflow-hidden relative">
                    <img src="https://res.cloudinary.com/dz4pww2qv/image/upload/v1728568781/bqxhpotosrxbg9eqgcgc.png" alt="article" class="absolute inset-0 w-full h-full object-cover">
                </div>
                <h3 class="text-xl font-semibold mt-4">${article.title}</h3>
                <p class="text-gray-500 text-sm">
                    <span class="font-bold">BY : </span>
                    <c:choose>
                        <c:when test="${article.editor != null}">
                            ${article.editor.firstName} ${article.editor.lastName}
                        </c:when>
                        <c:otherwise>
                            Unknown Editor
                        </c:otherwise>
                    </c:choose>
                </p>
                <p class="text-gray-600 mt-2">
                    <c:choose>
                        <c:when test="${fn:length(article.contenu) > 100}">
                            ${fn:substring(article.contenu, 0, 100)}...
                        </c:when>
                        <c:otherwise>
                            ${article.contenu}
                        </c:otherwise>
                    </c:choose>
                </p>
                <a href="#" class="text-blue-500 hover:underline mt-4 block">View more</a>
            </div>
        </c:forEach>
    </div>

    <div class="mt-12 flex justify-center space-x-1 my-12">
        <!-- Previous Button -->
        <c:if test="${currentPage > 1}">
            <a href="${pageContext.request.contextPath}/articles?page=${currentPage - 1}"
               class="px-4 py-2 bg-gray-300 text-gray-800 font-semibold rounded-l-lg hover:bg-gray-400 hover:text-gray-900 transition-colors duration-200">
                Previous
            </a>
        </c:if>

        <!-- Page Numbers -->
        <c:forEach var="i" begin="1" end="${totalPages}">
            <a href="${pageContext.request.contextPath}/articles?page=${i}"
               class="px-4 py-2 font-semibold text-gray-600 hover:text-gray-900 transition-colors duration-200
           ${i == currentPage ? 'bg-gray-800 text-white' : 'bg-gray-200 hover:bg-gray-300'} rounded-lg shadow-md">
                    ${i}
            </a>
        </c:forEach>

        <!-- Next Button -->
        <c:if test="${currentPage < totalPages}">
            <a href="${pageContext.request.contextPath}/articles?page=${currentPage + 1}"
               class="px-4 py-2 bg-gray-300 text-gray-800 font-semibold rounded-r-lg hover:bg-gray-400 hover:text-gray-900 transition-colors duration-200">
                Next
            </a>
        </c:if>
    </div>
</main>

<!-- Modal for adding an article -->
<div id="addArticleModal" class="fixed inset-0 bg-gray-800 bg-opacity-75 flex justify-center items-center hidden">
    <div class="bg-white rounded-lg shadow-lg w-1/3 p-6">
        <h2 class="text-2xl font-semibold mb-4">Add New Article</h2>
        <form action="<%=request.getContextPath()%>/articles?action=insert" method="post">
            <div class="mb-4">
                <label for="title" class="block text-gray-700">Title</label>
                <input type="hidden" name="action" value="insert">
                <input type="hidden" name="editor_id" value="${sessionScope.authorId}">
                <input type="text" id="title" name="title" class="w-full px-3 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500" required>
            </div>
            <div class="mb-4">
                <label for="content" class="block text-gray-700">Content</label>
                <textarea id="content" name="content" rows="4" class="w-full px-3 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500" required></textarea>
            </div>
            <div class="flex justify-end space-x-2">
                <button type="button" onclick="closeModal()" class="px-4 py-2 bg-gray-400 text-white rounded-md hover:bg-gray-500">Cancel</button>
                <button type="submit" class="px-4 py-2 bg-black text-white rounded-md hover:bg-gray-800">Add Article</button>
            </div>
        </form>
    </div>
</div>

<!-- Modal for error messages -->
<div id="errorModal" class="fixed inset-0 bg-gray-800 bg-opacity-75 flex justify-center items-center hidden">
    <div class="bg-white rounded-lg shadow-lg w-1/3 p-6">
        <h2 class="text-2xl font-semibold mb-4">Error</h2>
        <p class="text-red-500">${errorMessage}</p>
        <div class="flex justify-end space-x-2">
            <button type="button" onclick="closeErrorModal()" class="px-4 py-2 bg-gray-400 text-white rounded-md hover:bg-gray-500">Close</button>
        </div>
    </div>
</div>

<!-- JavaScript to handle modals -->
<script>
    function openModal() {
        document.getElementById('addArticleModal').classList.remove('hidden');
    }

    function closeModal() {
        document.getElementById('addArticleModal').classList.add('hidden');
    }

    // Check if there's an error message to display
    <c:if test="${not empty errorMessage}">
    document.getElementById('errorModal').classList.remove('hidden');
    </c:if>

    function closeErrorModal() {
        document.getElementById('errorModal').classList.add('hidden');
    }
</script>

</body>
</html>
