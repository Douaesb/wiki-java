<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Authentification - Cabinet</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100">

<div class="flex h-screen items-center justify-center">
    <div class="bg-white shadow-md rounded-lg p-6 flex w-3/4 max-w-5xl">
        <!-- Left Section: Form -->
        <div class="w-1/2 flex flex-col justify-center">
            <h1 class="text-2xl font-semibold mb-4 text-center">Bienvenue dans notre blog platform</h1>
            <p class="text-center mb-6 text-gray-600">Entrez ton email s'il vous plait pour te connecter</p>

            <form action="/login" method="POST" class="space-y-4">
                <div>
                    <label for="email" class="block text-gray-700 font-medium mb-2">Email</label>
                    <input type="email" id="email" name="email" placeholder="Email"
                           class="w-full px-4 py-2 border border-gray-300 rounded-lg shadow-sm focus:outline-none focus:ring focus:border-blue-500">
                </div>
                <div>
                    <button type="submit" class="w-full py-2 bg-gradient-to-r from-gray-400 to-gray-600 text-white font-semibold rounded-lg hover:from-gray-500 hover:to-gray-700 focus:outline-none focus:ring-2 focus:ring-gray-500">
                        CONNEXION
                    </button>
                </div>
            </form>
        </div>

        <!-- Right Section: Image -->
        <div class="w-1/2 flex items-center justify-center">
            <div class="overflow-hidden rounded-lg">
                <img src="https://res.cloudinary.com/dz4pww2qv/image/upload/v1728635073/go7duzv0h5bocybjguw6.png" alt="City view" class="object-cover h-96 w-full rounded-xl">
            </div>
        </div>
    </div>
</div>

</body>
</html>