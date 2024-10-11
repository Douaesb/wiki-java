
    <form action="updateAuthor" method="post" class="bg-gray-100 p-6 w-full max-w-md mx-auto rounded-lg shadow-md">
        <h2 class="text-2xl font-semibold mb-4 text-gray-700 text-center">Edit Author</h2>

        <input type="hidden" id="authorId" name="authorId" />

        <div class="mb-4">
            <label for="editfirstName" class="block text-sm font-medium text-gray-600">First Name</label>
            <input type="text" id="editfirstName" name="firstName" class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm" required>
        </div>

        <div class="mb-4">
            <label for="editlastName" class="block text-sm font-medium text-gray-600">Last Name</label>
            <input type="text" id="editlastName" name="lastName" class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm" required>
        </div>

        <div class="mb-4">
            <label for="editemail" class="block text-sm font-medium text-gray-600">Email</label>
            <input type="email" id="editemail" name="email" class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm" required>
        </div>

        <div class="mb-4">
            <label for="editdate" class="block text-sm font-medium text-gray-600">Date</label>
            <input type="date" id="editdate" name="birthDate" class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm" required>
        </div>


        <div class="mb-4">
            <label for="editrole" class="block text-sm font-medium text-gray-600">Role</label>
            <select id="editrole" name="role" class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm" required>
                <option value="">Select a role</option>
                <option value="Contributor">Contributor</option>
                <option value="Editor">Editor</option>
            </select>
        </div>


        <div class="flex justify-end">
            <button type="submit" class="text-white bg-slate-800 hover:bg-slate-700 px-4 py-2 rounded-lg">
                Save Changes
            </button>
        </div>
    </form>

<script>

    function openEditPopup(button) {

        const authorId = button.dataset.id;
        const firstName = button.dataset.firstname;
        const lastName = button.dataset.lastname;
        const email = button.dataset.email;
        const role = button.dataset.role;
        const date = button.dataset.date;


        document.getElementById('authorId').value = authorId;
        document.getElementById('editfirstName').value = firstName;
        document.getElementById('editlastName').value = lastName;
        document.getElementById('editemail').value = email;
        document.getElementById('editrole').value = role;
        document.getElementById('editdate').value = date;

        const roleSelect = document.getElementById('editrole');

        if (role.toLowerCase() === "contributor") {
            roleSelect.value = "Contributor";
        } else if (role.toLowerCase() === "editor") {
            roleSelect.value = "Editor";
        } else {
            roleSelect.value = "";
        }
        document.getElementById('editPopup').classList.remove('hidden');
    }


</script>