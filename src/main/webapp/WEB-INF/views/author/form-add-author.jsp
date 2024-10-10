<button class="absolute top-2 right-2 text-gray-600 text-3xl" onclick="document.getElementById('popupForm').classList.add('hidden')">&times;</button>


<form action="addAuthor" method="post" class="bg-gray-100 p-6 w-full max-w-md mx-auto rounded-lg shadow-md">
    <h2 class="text-2xl font-semibold mb-4 text-gray-700 text-center">Add New Author</h2>

    <div class="mb-4">
        <label for="firstName" class="block text-sm font-medium text-gray-600">First Name</label>
        <input type="text" id="firstName" name="firstName" class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-gray-400 focus:border-gray-400" placeholder="John" required>
    </div>

    <div class="mb-4">
        <label for="lastName" class="block text-sm font-medium text-gray-600">Last Name</label>
        <input type="text" id="lastName" name="lastName" class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-gray-400 focus:border-gray-400" placeholder="Doe" required>
    </div>

    <div class="mb-4">
        <label for="email" class="block text-sm font-medium text-gray-600">Email</label>
        <input type="email" id="email" name="email" class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-gray-400 focus:border-gray-400" placeholder="john.doe@example.com" required>
    </div>

    <div class="mb-4">
        <label for="role" class="block text-sm font-medium text-gray-600">Role</label>
        <select id="role" name="role" class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-gray-400 focus:border-gray-400" required>
            <option value="">Select Role</option>
            <option value="Contributor">Contributor</option>
            <option value="Editor">Editor</option>
        </select>
    </div>

    <div class="mb-4">
        <label for="birthdate" class="block text-sm font-medium text-gray-600">Birthdate</label>
        <input type="date" id="birthdate" name="birthdate" class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-gray-400 focus:border-gray-400" required>
    </div>

    <div class="mt-6 flex justify-center ">
        <button type="submit" class="bg-slate-800 hover:bg-gray-700 text-white py-2 px-4 rounded-md">Add Author</button>
    </div>
</form>
