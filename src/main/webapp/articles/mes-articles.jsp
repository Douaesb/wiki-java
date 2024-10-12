<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Articles</title>
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
            <a href="<%=request.getContextPath()%>/articles?action=list" class="text-gray-600 hover:text-gray-800">Articles</a>

            <!-- Check if the author's role is EDITOR -->
            <c:if test="${sessionScope.authorRole == 'EDITOR'}">
                <a href="<%=request.getContextPath()%>/articles?action=malist"
                   class="text-gray-600 hover:text-gray-800">Mes Articles</a>
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

<div class="text-gray-600 text-lg mt-4">
    <c:if test="${not empty sessionScope.authorName && not empty sessionScope.authorRole}">
        Welcome, <strong>${sessionScope.authorName}</strong>! Your role: <strong>${sessionScope.authorRole}</strong>
    </c:if>
</div>

<!-- Main Content -->
<main class="max-w-7xl mx-auto mt-8 px-4">
    <h2 class="text-3xl font-semibold text-gray-800 mb-6">My Articles</h2>

    <!-- Article Cards Grid -->
    <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        <c:forEach var="article" items="${articles}">
            <div class="bg-white p-4 rounded shadow-md border relative">
                <div class="h-48 bg-gray-300 overflow-hidden relative">
                    <img src="https://res.cloudinary.com/dz4pww2qv/image/upload/v1728568781/bqxhpotosrxbg9eqgcgc.png"
                         alt="article" class="absolute inset-0 w-full h-full object-cover">
                </div>
                <h3 class="text-xl font-semibold mt-4">${article.title}</h3>
                <p class="text-gray-600 mt-2">${article.contenu}</p>

                <div class="icons flex gap-3 mt-2">
                    <!-- Edit Button -->
                    <div
                            data-modal-target="crud-modal"
                            data-modal-toggle="crud-modal"
                            class="showModal"
                            data-id="<c:out value='${article.id}' />"
                            data-content="<c:out value='${article.contenu}' />"
                            data-articleid="<c:out value='${article.id}' />"
                            onclick="openEditModal('${article.title}', '${article.contenu}', ${article.id})"
                    >
                        <svg class="w-6 h-6 cursor-pointer text-blue-500 hover:text-blue-700" fill="currentColor" viewBox="0 0 24 24">
                            <path d="M3.99 16.854L2.685 20.358a.75.75 0 0 0 .966.965l3.503-1.314a3 3 0 0 0 1.068-.687L18.36 9.175s-.354-1.061-1.414-2.122c-1.06-1.06-2.122-1.414-2.122-1.414L4.677 15.786a3 3 0 0 0-.687 1.068zm12.249-12.63l1.383-1.383c.248-.248.579-.406.925-.348.487.08 1.232.322 1.934 1.025.703.703.945 1.447 1.025 1.934.058.346-.1.677-.348.925L19.774 7.76s-.353-1.06-1.414-2.12c-1.06-1.062-2.121-1.415-2.121-1.415z"></path>
                        </svg>
                    </div>

                    <!-- Delete Form -->
                    <form action="<%=request.getContextPath()%>/articles?action=delete" method="post" class="inline-flex items-center">
                        <input type="hidden"  name="articleId" value="${article.id}">
                        <button
                                type="submit"
                                onclick="return confirm('Are you sure you want to delete this comment?');"
                                class="text-red-500 hover:text-red-700"
                        >
                            <svg class="w-6 h-6" fill="currentColor" viewBox="0 0 24 24">
                                <path d="M5.755,20.283,4,8H20L18.245,20.283A2,2,0,0,1,16.265,22H7.735A2,2,0,0,1,5.755,20.283ZM21,4H16V3a1,1,0,0,0-1-1H9A1,1,0,0,0,8,3V4H3A1,1,0,0,0,3,6H21a1,1,0,0,0,0-2Z"></path>
                            </svg>
                        </button>
                    </form>
                </div>

            </div>
        </c:forEach>
    </div>
</main>

<!-- Edit Article Modal -->
<div id="editArticleModal" class="fixed inset-0 bg-gray-800 bg-opacity-75 flex justify-center items-center hidden">
    <div class="bg-white rounded-lg shadow-lg w-1/3 p-6">
        <h2 class="text-2xl font-semibold mb-4">Edit Article</h2>
        <form id="editArticleForm" action="<%=request.getContextPath()%>/articles?action=update" method="post">
            <!-- Fix the name attribute to match servlet parameter expectation -->
            <input type="hidden" id="articleId" name="articleId">

            <div class="mb-4">
                <label for="edit_title" class="block text-gray-700">Title</label>
                <input type="text" id="edit_title" name="title" class="w-full px-3 py-2 border rounded-md" required>
            </div>

            <div class="mb-4">
                <label for="edit_content" class="block text-gray-700">Content</label>
                <textarea id="edit_content" name="content" rows="4" class="w-full px-3 py-2 border rounded-md" required></textarea>
            </div>

            <div class="flex justify-end space-x-2">
                <button type="button" onclick="closeEditModal()" class="px-4 py-2 bg-gray-400 text-white rounded-md">Cancel</button>
                <button type="submit" class="px-4 py-2 bg-black text-white rounded-md">Update Article</button>
            </div>
        </form>
    </div>
</div>


<!-- JavaScript to handle the edit modal -->
<script>
    function openEditModal(title, content, articleId) {
        document.getElementById('edit_title').value = title;
        document.getElementById('edit_content').value = content;
        document.getElementById('articleId').value = articleId;  // Correct the 'articleId'

        document.getElementById('editArticleModal').classList.remove('hidden');
    }

    function closeEditModal() {
        document.getElementById('editArticleModal').classList.add('hidden');

    }
</script>

</body>
</html>
