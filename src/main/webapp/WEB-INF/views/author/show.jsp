<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="bg-white shadow-md rounded-lg overflow-hidden">
    <table class="min-w-full divide-y divide-gray-200">
        <thead class="bg-gray-50">
        <tr>
            <th class="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Full Name
            </th>
            <th class="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Role</th>
            <th class="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Email</th>
            <th class="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Date of
                Birth
            </th>
            <th class="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Actions</th>
        </tr>
        </thead>
        <tbody class="bg-white divide-y divide-gray-200">
        <c:forEach var="author" items="${authors}">
            <tr>
                <td class="px-4 py-4 whitespace-nowrap flex items-center">
                    <div class="w-8 h-8 flex items-center justify-center rounded-full bg-blue-600 text-white mr-2">
                        <c:out value="${author.id}"/> <!-- Remplacez par une méthode pour obtenir les initiales -->
                    </div>
                    <c:out value="${author.firstName}  ${author.lastName}"/>
                </td>
                <td class="px-4 py-4 whitespace-nowrap">
                            <span class="px-2 inline-flex text-xs leading-5 font-semibold rounded-full bg-green-100 text-green-800">
                                <c:out value="${author.role}"/> <!-- Remplacez par le rôle de l'auteur -->
                            </span>
                </td>
                <td class="px-4 py-4 whitespace-nowrap"><c:out value="${author.email}"/></td>

                <td class="px-4 py-4 whitespace-nowrap"><c:out value="${author.dateOfBirth}"/></td>
                <td class="px-4 py-4 whitespace-nowrap text-sm font-medium">
                    <button class="text-blue-600 hover:text-blue-900 mr-3"
                            data-id="${author.id}"
                            data-firstname="${author.firstName}"
                            data-lastname="${author.lastName}"
                            data-email="${author.email}"
                            data-role="${author.role}"
                            data-date="${author.dateOfBirth}"
                            onclick="openEditPopup(this)">
                        <svg class="w-6 h-6" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd" clip-rule="evenodd"
                                  d="m3.99 16.854-1.314 3.504a.75.75 0 0 0 .966.965l3.503-1.314a3 3 0 0 0 1.068-.687L18.36 9.175s-.354-1.061-1.414-2.122c-1.06-1.06-2.122-1.414-2.122-1.414L4.677 15.786a3 3 0 0 0-.687 1.068zm12.249-12.63 1.383-1.383c.248-.248.579-.406.925-.348.487.08 1.232.322 1.934 1.025.703.703.945 1.447 1.025 1.934.058.346-.1.677-.348.925L19.774 7.76s-.353-1.06-1.414-2.12c-1.06-1.062-2.121-1.415-2.121-1.415z"
                                  fill="#2563eb"></path>
                        </svg>
                    </button>
                    <form id="deleteForm" method="post" action="deleteAuthor" style="display:inline;">
                        <input type="hidden" id="authorIdToDelete" name="id" value="">
                        <button type="button" class="text-red-600 hover:text-red-900" aria-label="Delete"
                                onclick="confirmDelete(${author.id})">
                            <!-- SVG for Delete -->
                            <svg class="w-6 h-6" fill="#dc2626" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                                <path d="M5.755,20.283,4,8H20L18.245,20.283A2,2,0,0,1,16.265,22H7.735A2,2,0,0,1,5.755,20.283ZM21,4H16V3a1,1,0,0,0-1-1H9A1,1,0,0,0,8,3V4H3A1,1,0,0,0,3,6H21a1,1,0,0,0,0-2Z"></path>
                            </svg>
                        </button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<div class="mt-6 flex justify-center">
    <div class="flex space-x-1">
        <a href="${currentPage > 1 ? 'authors?page='.concat(currentPage - 1) : '#'}"
           class="rounded-full bg-white border border-slate-300 py-2 px-3 text-center text-sm transition-all shadow-sm hover:shadow-lg text-slate-600 hover:text-white hover:bg-slate-800 hover:border-slate-800 focus:text-white focus:bg-slate-800 focus:border-slate-800 active:border-slate-800 active:text-white active:bg-slate-800 ${currentPage == 1 ? 'opacity-50 cursor-not-allowed' : ''}"
        ${currentPage == 1 ? 'onclick="return false;"' : ''}>
            Prev
        </a>

        <c:forEach begin="1" end="${noOfPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <span class="min-w-9 rounded-full bg-slate-800 py-2 px-3.5 border border-transparent text-center text-sm text-white transition-all shadow-md hover:shadow-lg focus:bg-slate-700 focus:shadow-none active:bg-slate-700 hover:bg-slate-700 active:shadow-none disabled:pointer-events-none disabled:opacity-50 disabled:shadow-none ml-2">
                            ${i}
                    </span>
                </c:when>
                <c:otherwise>
                    <a href="authors?page=${i}"
                       class="min-w-9 bg-white rounded-full border border-slate-300 py-2 px-3.5 text-center text-sm transition-all shadow-sm hover:shadow-lg text-slate-600 hover:text-white hover:bg-slate-800 hover:border-slate-800 focus:text-white focus:bg-slate-800 focus:border-slate-800 active:border-slate-800 active:text-white active:bg-slate-800 disabled:pointer-events-none disabled:opacity-50 disabled:shadow-none ml-2">
                            ${i}
                    </a>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <a href="${currentPage < noOfPages ? 'authors?page='.concat(currentPage + 1) : '#'}"
           class="min-w-9 bg-white rounded-full border border-slate-300 py-2 px-3 text-center text-sm transition-all shadow-sm hover:shadow-lg text-slate-600 hover:text-white hover:bg-slate-800 hover:border-slate-800 focus:text-white focus:bg-slate-800 focus:border-slate-800 active:border-slate-800 active:text-white active:bg-slate-800 ${currentPage == noOfPages ? 'opacity-50 cursor-not-allowed' : ''}"
        ${currentPage == noOfPages ? 'onclick="return false;"' : ''}>
            Next
        </a>
    </div>
</div>


<script>
    function confirmDelete(authorId) {
        if (confirm("Are you sure you want to delete this author?")) {
            document.getElementById('authorIdToDelete').value = authorId;
            document.getElementById('deleteForm').submit();
        }
    }


</script>