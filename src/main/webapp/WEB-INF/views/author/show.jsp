<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion des Auteurs</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100">
<nav class="bg-indigo-600 text-white shadow-lg">
    <div class="container mx-auto px-6 py-3 flex justify-between items-center">
        <div class="flex space-x-4">
            <a href="#" class="font-bold text-xl">MonBlog</a>
            <a href="#" class="hover:text-indigo-200">Articles</a>
            <a href="#" class="hover:text-indigo-200">Auteurs</a>
            <a href="#" class="hover:text-indigo-200">Commentaires</a>
        </div>

    </div>
</nav>

<main class="container mx-auto mt-8 px-4">
    <h1 class="text-3xl font-bold mb-6 text-gray-800">Liste des Auteurs</h1>

    <div class="flex justify-end mb-4">
        <button class="text-white text-end bg-indigo-600  hover:text-indigo-600 px-4 py-2 rounded-lg hover:bg-indigo-100 transition duration-300">
            Ajouter un auteur
        </button>
    </div>


    <div class="bg-white shadow-md rounded-lg overflow-hidden">
        <table class="min-w-full divide-y divide-gray-200">
            <thead class="bg-gray-50">
            <tr>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Nom</th>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Prénom</th>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Email</th>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Date de naissance</th>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Rôle</th>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Actions</th>
            </tr>
            </thead>
            <tbody class="bg-white divide-y divide-gray-200">
            <tr>
                <td class="px-6 py-4 whitespace-nowrap">Idelkadi</td>
                <td class="px-6 py-4 whitespace-nowrap">Radia</td>
                <td class="px-6 py-4 whitespace-nowrap">idelkadiradia@gmail.com</td>
                <td class="px-6 py-4 whitespace-nowrap">23/04/18</td>
                <td class="px-6 py-4 whitespace-nowrap">
                            <span class="px-2 inline-flex text-xs leading-5 font-semibold rounded-full bg-green-100 text-green-800">
                                Contributeur
                            </span>
                </td>
                <td class="px-6 py-4 whitespace-nowrap text-sm font-medium">
                    <button class="text-indigo-600 hover:text-indigo-900 mr-3">Éditer</button>
                    <button class="text-red-600 hover:text-red-900">Supprimer</button>
                </td>
            </tr>
            <!-- Répétez cette structure pour chaque auteur -->
            </tbody>
        </table>
    </div>

    <div class="mt-6 flex justify-center">
        <nav class="relative z-0 inline-flex gap-x-2 rounded-md  -space-x-px" aria-label="Pagination">
            <a href="#" class="relative inline-flex items-center px-2 py-2 rounded-l-md border border-gray-300 bg-white text-sm font-medium text-gray-500 hover:bg-gray-50">
                <span class="sr-only">Précédent</span>
                <svg class="h-5 w-5" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor" aria-hidden="true">
                    <path fill-rule="evenodd" d="M12.707 5.293a1 1 0 010 1.414L9.414 10l3.293 3.293a1 1 0 01-1.414 1.414l-4-4a1 1 0 010-1.414l4-4a1 1 0 011.414 0z" clip-rule="evenodd" />
                </svg>
            </a>
            <a href="#" class="relative rounded-lg inline-flex items-center px-4 py-2 border border-gray-300 bg-white text-sm font-medium text-gray-700 hover:bg-gray-50">
                1
            </a>
            <a href="#" class="relative rounded-lg inline-flex items-center px-4 py-2 border border-gray-300 bg-white text-sm font-medium text-gray-700 hover:bg-gray-50">
                2
            </a>
            <a href="#" class="relative rounded-lg inline-flex items-center px-4 py-2 border border-gray-300 bg-white text-sm font-medium text-gray-700 hover:bg-gray-50">
                3
            </a>
            <a href="#" class="relative  inline-flex items-center px-2 py-2 rounded-r-md border border-gray-300 bg-white text-sm font-medium text-gray-500 hover:bg-gray-50">
                <span class="sr-only">Suivant</span>
                <!-- Heroicon name: solid/chevron-right -->
                <svg class="h-5 w-5" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor" aria-hidden="true">
                    <path fill-rule="evenodd" d="M7.293 14.707a1 1 0 010-1.414L10.586 10 7.293 6.707a1 1 0 011.414-1.414l4 4a1 1 0 010 1.414l-4 4a1 1 0 01-1.414 0z" clip-rule="evenodd" />
                </svg>
            </a>
        </nav>
    </div>
</main>
</body>
</html>