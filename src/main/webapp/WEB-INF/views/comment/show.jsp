<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/flowbite@2.5.2/dist/flowbite.min.css" rel="stylesheet" />

    <title>Article Page</title>
</head>
<body class="flex flex-col items-center justify-between h-screen p-4">

<!-- Back Arrow Section -->
<div class="flex flex-row w-3/6 mt-12">
    <a href="${pageContext.request.contextPath}/articles" class="text-blue-500 hover:text-blue-700">
        <img src=" https://res.cloudinary.com/dz4pww2qv/image/upload/v1728637987/wmp85euw48itmvuwfbni.png" alt="Article Image" class=" w-8">
    </a>
</div>

<!-- Title Section -->
<h1 class="text-center text-3xl font-bold w-3/6 mt-6">${article.title}</h1>

<!-- Author Section -->
<p class=" flex flex-row w-3/6 text-gray-600 mt-4 ml-4">${article.editor.firstName} ${article.editor.lastName} | ${article.creationDate}</p>

<!-- Image Section -->
<div class="mt-6">
    <img src="https://res.cloudinary.com/dz4pww2qv/image/upload/v1728568781/bqxhpotosrxbg9eqgcgc.png" alt="Article Image" class="rounded-lg shadow-md w-full max-w-2xl">
</div>

<!-- Article Content -->
<div class="flex flex-col  items-center mt-6 w-full max-w-3xl text-left">
    <p class="text-gray-800 mb-4">${article.contenu}</p>

</div>

<hr class="w-3/6">
<!-- Comments Section -->
<div class="mt-6 w-full max-w-xl mx-auto">
    <div class="flex justify-between w-full items-start">
        <div class="flex gap-2">
            <h2 class="text-xl font-semibold">Comments</h2>
            <span class="text-xl font-semibold">(${totalComments})</span>
        </div>
        <div class="flex gap-6 pb-3">
            <button id="all-comments" class="bg-gray-500 rounded-lg p-2 text-white"
                     >All comments</button>
<c:if test="${sessionScope.authorRole == 'CONTRIBUTOR'}">

<button id="my-comments" class="rounded-lg border border-gray-500 p-2 active-button"
                   >
                My comments
            </button>
</c:if>

        </div>
    </div>

    <!-- All Comments Section -->

    <div id="all-comments-section" class="w-full rounded-md p-2 mt-2">
<c:if test="${sessionScope.authorRole == 'CONTRIBUTOR'}">

<div class="shadow-lg border border-gray-300 p-2 rounded-md">
            <form action="comments?action=create" method="POST">
                <label>
                    <textarea name="content" class="w-5/6 border-none focus:border-none " rows="1" placeholder="Add a comment..." required></textarea>
                </label>
                <input type="hidden" name="articleId" value="${article.id}">
                <input type="hidden" name="contributorId" value="${sessionScope.authorId}">
                <button type="submit" class="ml-3 bg-gray-500 rounded-lg p-2 text-white mb-3">Publish</button>
            </form>
        </div>
</c:if>



        <div class="mb-6">
            <c:forEach var="comment" items="${comments}">
                <div class="w-full shadow-lg border border-gray-300 rounded-md p-2 mt-2">
                    <div class="flex justify-between w-full pt-3">
                        <div class="flex w-full gap-3">
                            <img src="https://res.cloudinary.com/dz4pww2qv/image/upload/v1728568781/bqxhpotosrxbg9eqgcgc.png" alt="Article Image" class="rounded-full h-10 w-10">
                            <span class="mt-1 text-center items-center">
                                ${comment.contributor.firstName} ${comment.contributor.lastName} * <span class="mt-1 text-center items-center">${comment.creationDate}</span>
                            </span>
                        </div>

                    </div>
                    <p class="pt-4">${comment.content}</p>
                </div>
            </c:forEach>
        </div>
        <!--  pagination All comments -->

        <div class="flex items-center justify-center border-t border-gray-200 bg-white w-full px-4 py-3 sm:px-6">
            <div class="flex flex-1 justify-between sm:hidden">
                <a href="?action=view&id=${article.id}&page=${currentPage - 1}&pageSize=${pageSize}"
                   class="relative inline-flex items-center rounded-md border border-gray-300 bg-white px-4 py-2 text-sm font-medium text-gray-700 hover:bg-gray-50 ${currentPage == 1 ? 'cursor-not-allowed' : ''}">Previous</a>
                <a href="?action=view&id=${article.id}&page=${currentPage + 1}&pageSize=${pageSize}"
                   class="relative ml-3 inline-flex items-center rounded-md border border-gray-300 bg-white px-4 py-2 text-sm font-medium text-gray-700 hover:bg-gray-50 ${currentPage == totalPages ? 'cursor-not-allowed' : ''}">Next</a>
            </div>
            <div class="hidden sm:flex sm:flex-1 sm:items-center sm:justify-center">
                <nav class="isolate inline-flex -space-x-px rounded-md shadow-sm" aria-label="Pagination">
                    <a href="?action=view&id=${article.id}&page=${currentPage - 1}&pageSize=${pageSize}"
                       class="relative inline-flex items-center rounded-l-md px-2 py-2 text-gray-400 ring-1 ring-inset ring-gray-300 hover:bg-gray-50 ${currentPage == 1 ? 'cursor-not-allowed' : ''}">
                        <svg class="h-5 w-5 mt-1" viewBox="0 0 20 20" fill="currentColor" aria-hidden="true" data-slot="icon">
                            <path fill-rule="evenodd" d="M11.78 5.22a.75.75 0 0 1 0 1.06L8.06 10l3.72 3.72a.75.75 0 1 1-1.06 1.06l-4.25-4.25a.75.75 0 0 1 0-1.06l4.25-4.25a.75.75 0 0 1 1.06 0Z" clip-rule="evenodd" />
                        </svg>
                        <span class="items-center">Previous</span>
                    </a>

                    <!-- Dynamically generate page links -->
                    <c:forEach var="i" begin="1" end="${totalPages}">
                        <a href="?action=view&id=${article.id}&page=${i}&pageSize=${pageSize}"
                           class="relative inline-flex items-center px-4 py-2 text-sm font-semibold ${i == currentPage ? 'bg-black text-white' : 'text-gray-900 ring-1 ring-inset ring-gray-300 hover:bg-gray-50'}">
                                ${i}
                        </a>
                    </c:forEach>

                    <a href="?action=view&id=${article.id}&page=${currentPage + 1}&pageSize=${pageSize}"
                       class="relative inline-flex items-center rounded-r-md px-2 py-2 text-gray-400 ring-1 ring-inset ring-gray-300 hover:bg-gray-50 ${currentPage == totalPages ? 'cursor-not-allowed' : ''}">
                        <span class="items-center">Next</span>

                        <svg class="h-5 w-5 mt-1" viewBox="0 0 20 20" fill="currentColor" aria-hidden="true" data-slot="icon">
                            <path fill-rule="evenodd" d="M8.22 5.22a.75.75 0 0 1 1.06 0l4.25 4.25a.75.75 0 0 1 0 1.06l-4.25 4.25a.75.75 0 0 1-1.06-1.06L11.94 10 8.22 6.28a.75.75 0 0 1 0-1.06Z" clip-rule="evenodd" />
                        </svg>

                    </a>
                </nav>
            </div>
        </div>
    </div>

    <!-- My Comments Section -->
    <div id="my-comments-section" class="hidden w-full  rounded-md p-2 mt-2">
        <div class="mb-6">
            <c:forEach var="myComment" items="${myComments}">
                <div class="w-full shadow-lg border border-gray-300 rounded-md p-2 mt-2">
                    <div class="flex justify-between w-full pt-3">
                        <div class="flex w-full gap-3">
                            <img src="https://res.cloudinary.com/dz4pww2qv/image/upload/v1728568781/bqxhpotosrxbg9eqgcgc.png" alt="Article Image" class="rounded-full h-10 w-10">
                            <span class="mt-1 text-center items-center">
                                ${myComment.contributor.firstName} ${myComment.contributor.lastName} * <span class="mt-1 text-center items-center">${myComment.creationDate}</span>
                            </span>
                        </div>
                        <div class="icons flex gap-3 mt-2">
                            <div data-modal-target="crud-modal" data-modal-toggle="crud-modal" class="showModal" data-id="<c:out value='${myComment.id}' />"
                                 data-content="<c:out value='${myComment.content}' />"  data-articleid="<c:out value='${article.id}' />">
                                <svg class="w-6 h-6" fill="#000000" viewBox="0 0 24 24"><path d="M3.99 16.854L2.685 20.358a.75.75 0 0 0 .966.965l3.503-1.314a3 3 0 0 0 1.068-.687L18.36 9.175s-.354-1.061-1.414-2.122c-1.06-1.06-2.122-1.414-2.122-1.414L4.677 15.786a3 3 0 0 0-.687 1.068zm12.249-12.63l1.383-1.383c.248-.248.579-.406.925-.348.487.08 1.232.322 1.934 1.025.703.703.945 1.447 1.025 1.934.058.346-.1.677-.348.925L19.774 7.76s-.353-1.06-1.414-2.12c-1.06-1.062-2.121-1.415-2.121-1.415z"></path></svg>
                            </div>
                            <form action="comments?action=delete" method="post">
                                <input type="hidden" name="commentId" value="${myComment.id}">
                                <input type="hidden" name="articleId" value="${article.id}">
                                <input type="hidden" name="contributorId" value="${sessionScope.authorId}">

                                <button type="submit" onclick="return confirm('Are you sure you want to delete this comment?');">
                                    <svg class="w-6 h-6" fill="#000000" viewBox="0 0 24 24">
                                        <path d="M5.755,20.283,4,8H20L18.245,20.283A2,2,0,0,1,16.265,22H7.735A2,2,0,0,1,5.755,20.283ZM21,4H16V3a1,1,0,0,0-1-1H9A1,1,0,0,0,8,3V4H3A1,1,0,0,0,3,6H21a1,1,0,0,0,0-2Z"></path>
                                    </svg>
                                </button>
                            </form>
                        </div>
                    </div>
                    <p class="pt-4">${myComment.content}</p>
                </div>
            </c:forEach>

        </div>
        <!--  pagination my comments -->
        <div class="flex items-center justify-center border-t border-gray-200 bg-white w-full px-4 py-3 sm:px-6">
            <div class="flex flex-1 justify-between sm:hidden">
                <a href="?action=view&id=${article.id}&contributorId=${contributorId}&myPage=${currentMyPage - 1}&pageSize=${pageSize}"
                   class="relative inline-flex items-center rounded-md border border-gray-300 bg-white px-4 py-2 text-sm font-medium text-gray-700 hover:bg-gray-50 ${currentMyPage == 1 ? 'cursor-not-allowed' : ''}">Previous</a>
                <a href="?action=view&id=${article.id}&contributorId=${contributorId}&myPage=${currentMyPage + 1}&pageSize=${pageSize}"
                   class="relative ml-3 inline-flex items-center rounded-md border border-gray-300 bg-white px-4 py-2 text-sm font-medium text-gray-700 hover:bg-gray-50 ${currentMyPage == totalMyPages ? 'cursor-not-allowed' : ''}">Next</a>
            </div>
            <div class="hidden sm:flex sm:flex-1 sm:items-center sm:justify-center">
                <nav class="isolate inline-flex -space-x-px rounded-md shadow-sm" aria-label="Pagination">
                    <a href="?action=view&id=${article.id}&contributorId=${contributorId}&myPage=${currentMyPage - 1}&pageSize=${pageSize}"
                       class="relative inline-flex items-center rounded-l-md px-2 py-2 text-gray-400 ring-1 ring-inset ring-gray-300 hover:bg-gray-50 ${currentMyPage == 1 ? 'cursor-not-allowed' : ''}">
                        <svg class="h-5 w-5 mt-1" viewBox="0 0 20 20" fill="currentColor" aria-hidden="true" data-slot="icon">
                            <path fill-rule="evenodd" d="M11.78 5.22a.75.75 0 0 1 0 1.06L8.06 10l3.72 3.72a.75.75 0 1 1-1.06 1.06l-4.25-4.25a.75.75 0 0 1 0-1.06l4.25-4.25a.75.75 0 0 1 1.06 0Z" clip-rule="evenodd" />
                        </svg>
                        <span class="items-center">Previous</span>
                    </a>

                    <!-- Dynamically generate page links for my comments -->
                    <c:forEach var="i" begin="1" end="${totalMyPages}">
                        <a href="?action=view&id=${article.id}&contributorId=${contributorId}&myPage=${i}&pageSize=${pageSize}"
                           class="relative inline-flex items-center px-4 py-2 text-sm font-semibold ${i == currentMyPage ? 'bg-black text-white' : 'text-gray-900 ring-1 ring-inset ring-gray-300 hover:bg-gray-50'}">
                                ${i}
                        </a>
                    </c:forEach>

                    <a href="?action=view&id=${article.id}&contributorId=${contributorId}&myPage=${currentMyPage + 1}&pageSize=${pageSize}"
                       class="relative inline-flex items-center rounded-r-md px-2 py-2 text-gray-400 ring-1 ring-inset ring-gray-300 hover:bg-gray-50 ${currentMyPage == totalMyPages ? 'cursor-not-allowed' : ''}">
                        <span class="items-center">Next</span>
                        <svg class="h-5 w-5 mt-1" viewBox="0 0 20 20" fill="currentColor" aria-hidden="true" data-slot="icon">
                            <path fill-rule="evenodd" d="M8.22 5.22a.75.75 0 0 1 1.06 0l4.25 4.25a.75.75 0 0 1 0 1.06l-4.25 4.25a.75.75 0 0 1-1.06-1.06L11.94 10 8.22 6.28a.75.75 0 0 1 0-1.06Z" clip-rule="evenodd" />
                        </svg>
                    </a>
                </nav>
            </div>
        </div>
    </div>

    <!-- Main modal -->
    <div id="crud-modal" tabindex="-1" aria-hidden="true" class="hidden overflow-y-auto overflow-x-hidden fixed top-0 right-0 left-0 z-50 justify-center items-center w-full md:inset-0 h-[calc(100%-1rem)] max-h-full">
        <div class="relative p-4 w-full max-w-md max-h-full">
            <!-- Modal content -->
            <div class="relative bg-white rounded-lg shadow dark:bg-gray-700">
                <!-- Modal header -->
                <div class="flex items-center justify-between p-4 md:p-5 border-b rounded-t dark:border-gray-600">
                    <h3 class="text-lg font-semibold text-gray-900 dark:text-white">
                        Edit Comment
                    </h3>
                    <button type="button" class="text-gray-400 bg-transparent hover:bg-gray-200 hover:text-gray-900 rounded-lg text-sm w-8 h-8 ms-auto inline-flex justify-center items-center dark:hover:bg-gray-600 dark:hover:text-white" data-modal-toggle="crud-modal">
                        <svg class="w-3 h-3" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 14 14">
                            <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="m1 1 6 6m0 0 6 6M7 7l6-6M7 7l-6 6"/>
                        </svg>
                        <span class="sr-only">Close modal</span>
                    </button>
                </div>
                <!-- Modal body -->
                <form class="p-4 md:p-5" method="post" action="comments?action=update">
                    <div class="grid gap-4 mb-4 grid-cols-2">
                        <div class="col-span-2">
                            <input type="hidden" name="id" id="editId" required>
                            <input type="hidden" name="articleId" id="articleId" required>
                            <input type="hidden" name="contributorId" value="${sessionScope.authorId}">
                            <label for="description"></label>
                            <textarea id="description" name="content" rows="4" class="block p-2.5 w-full text-sm text-gray-900 bg-gray-50 rounded-lg border border-gray-300 focus:ring-blue-500 focus:border-blue-500 dark:bg-gray-600 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" placeholder="Write product description here"></textarea>
                        </div>
                    </div>
                    <button type="submit" class="text-white inline-flex items-center bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">
                        <svg class="me-1 -ms-1 w-5 h-5" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M10 5a1 1 0 011 1v3h3a1 1 0 110 2h-3v3a1 1 0 11-2 0v-3H6a1 1 0 110-2h3V6a1 1 0 011-1z" clip-rule="evenodd"></path></svg>
                        Edit Comment
                    </button>
                </form>
            </div>
        </div>

    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/flowbite@2.5.2/dist/flowbite.min.js"></script>
<script>
    document.addEventListener('DOMContentLoaded', function () {
        var triggers = document.querySelectorAll('.showModal');

        triggers.forEach(function(trigger) {
            trigger.addEventListener('click', function () {
                var id = this.getAttribute('data-id');
                var articleid = this.getAttribute('data-articleid');

                var content = this.getAttribute('data-content');

                console.log('ID:', id);
                console.log('Content:', content);

                document.getElementById('editId').value = id;
                document.getElementById('articleId').value = articleid;

                document.getElementById('description').value = content;

            });
        });
    });

    const allCommentsButton = document.getElementById("all-comments");
    const myCommentsButton = document.getElementById("my-comments");
    const allCommentsSection = document.getElementById("all-comments-section");
    const myCommentsSection = document.getElementById("my-comments-section");

    allCommentsButton.addEventListener("click", () => {
        allCommentsSection.classList.remove("hidden");
        myCommentsSection.classList.add("hidden");
        allCommentsButton.classList.add("bg-gray-500", "text-white");
        myCommentsButton.classList.remove("bg-gray-500", "text-white");
        myCommentsButton.classList.add("border-gray-500");
    });

    myCommentsButton.addEventListener("click", () => {
        myCommentsSection.classList.remove("hidden");
        allCommentsSection.classList.add("hidden");
        myCommentsButton.classList.add("bg-gray-500", "text-white");
        allCommentsButton.classList.remove("bg-gray-500", "text-white");
        allCommentsButton.classList.add("border");
        allCommentsButton.classList.add("border-gray-500");
    });

</script>
</body>
</html>
