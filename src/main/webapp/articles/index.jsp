<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
            <a href="#" class="text-gray-600 hover:text-gray-800">Articles</a>
            <a href="#" class="text-gray-600 hover:text-gray-800">Mes Articles</a>
        </nav>
    </div>
</header>

<!-- Search Bar Centered Below the Navbar -->
<div class="mt-4 flex justify-center">
    <div class="relative w-full md:w-1/2">
        <input type="text" placeholder="Search" class="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
        <button class="absolute inset-y-0 right-0 px-4 py-2 bg-black text-white rounded-r-md hover:bg-gray-800">Search</button>
    </div>
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
                <p class="text-gray-500 text-sm">Abdellah</p>
                <p class="text-gray-600 mt-2">${article.contenu}</p>
                <a href="#" class="text-blue-500 hover:underline mt-4 block">View more</a>
            </div>
        </c:forEach>
    </div>

    <!-- Pagination -->
    <div class="mt-12 flex justify-center space-x-2">
        <a href="#" class="px-4 py-2 bg-gray-200 text-gray-600 rounded-l hover:bg-gray-300">Previous</a>
        <a href="#" class="px-4 py-2 bg-gray-300 text-gray-600 hover:bg-gray-400">1</a>
        <a href="#" class="px-4 py-2 bg-gray-300 text-gray-600 hover:bg-gray-400">2</a>
        <a href="#" class="px-4 py-2 bg-gray-300 text-gray-600 hover:bg-gray-400">3</a>
        <a href="#" class="px-4 py-2 bg-gray-200 text-gray-600 rounded-r hover:bg-gray-300">Next</a>
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
