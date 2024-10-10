<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    <title>Article Page</title>
</head>
<body class="flex flex-col items-center justify-between h-screen p-4">

<!-- Back Arrow Section -->
<div class="flex flex-row w-3/6 mt-12">
    <a href="#" class="text-blue-500 hover:text-blue-700">
        <img src="./img/back-arrow.png" alt="Article Image" class=" w-8">
    </a>
</div>

<!-- Title Section -->
<h1 class="text-center text-3xl font-bold w-3/6 mt-6">Slowing growth in life expectancy means few people will live to 100</h1>

<!-- Author Section -->
<p class=" flex flex-row w-3/6 text-gray-600 mt-4 ml-4">by Author Name | a min ago </p>

<!-- Image Section -->
<div class="mt-6">
    <img src="./img/newspaper-background.png" alt="Article Image" class="rounded-lg shadow-md w-full max-w-2xl">
</div>

<!-- Article Content -->
<div class="flex flex-col  items-center mt-6 w-full max-w-3xl text-left">
    <p class="text-gray-800 mb-4">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec ligula nibh, interdum non enim sit amet, iaculis aliquet nunc. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Aliquam sit amet ipsum ac velit egestas ultrices. Vestibulum et neque id ex semper varius a sit amet metus. Vivamus congue dolor eget aliquam hendrerit. Etiam iaculis finibus egestas. Nam viverra urna quis odio efficitur malesuada. Maecenas rhoncus enim eu scelerisque rutrum. Pellentesque et mollis enim. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur sed commodo leo. Suspendisse potenti. Maecenas gravida ipsum placerat ligula posuere, ut rhoncus velit eleifend.</p>
    <p class="text-gray-800 mb-4">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec ligula nibh, interdum non enim sit amet, iaculis aliquet nunc. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Aliquam sit amet ipsum ac velit egestas ultrices. Vestibulum et neque id ex semper varius a sit amet metus. Vivamus congue dolor eget aliquam hendrerit. Etiam iaculis finibus egestas. Nam viverra urna quis odio efficitur malesuada. Maecenas rhoncus enim eu scelerisque rutrum. Pellentesque et mollis enim. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur sed commodo leo. Suspendisse potenti. Maecenas gravida ipsum placerat ligula posuere, ut rhoncus velit eleifend.</p>

</div>

<hr class="w-3/6">
<!-- Comments Section -->
<div class="mt-6 w-full max-w-xl">
    <div class="flex justify-between w-full items-start">


    <div class="flex gap-2">
        <h2 class="text-xl font-semibold">Comments</h2>
        <span class="text-xl font-semibold">(40)</span>
    </div>

    <div class="flex gap-6 pb-3">
        <button class="rounded-lg border border-gray-500 p-2">All comments</button>
        <button class="bg-gray-500 rounded-lg p-2">My comments</button>

    </div>
    </div>

    <div class="w-full shadow-lg border border-gray-300 rounded-md p-2 mt-2">
        <label>
            <textarea class="w-5/6" rows="1" placeholder="Add a comment..."></textarea>
        </label>
        <button class="ml-3 bg-gray-500 rounded-lg p-2 text-white">Publish</button>
    </div>

    <div class="mb-6">


    <div class="w-full shadow-lg border border-gray-300 rounded-md p-2 mt-2">
        <div class="flex justify-between w-full pt-3">
            <div class=" flex w-full gap-3">
                <img src="./img/newspaper-background.png" alt="Article Image" class="rounded-full h-10 w-10">
                <span class="mt-1 text-center items-center">User name  .  22 Jul</span>
            </div>
            <div class="icons flex gap-3 mt-2">
                <svg class="w-6 h-6" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg"><g id="SVGRepo_bgCarrier" stroke-width="0"></g><g id="SVGRepo_tracerCarrier" stroke-linecap="round" stroke-linejoin="round"></g><g id="SVGRepo_iconCarrier"><path fill-rule="evenodd" clip-rule="evenodd" d="m3.99 16.854-1.314 3.504a.75.75 0 0 0 .966.965l3.503-1.314a3 3 0 0 0 1.068-.687L18.36 9.175s-.354-1.061-1.414-2.122c-1.06-1.06-2.122-1.414-2.122-1.414L4.677 15.786a3 3 0 0 0-.687 1.068zm12.249-12.63 1.383-1.383c.248-.248.579-.406.925-.348.487.08 1.232.322 1.934 1.025.703.703.945 1.447 1.025 1.934.058.346-.1.677-.348.925L19.774 7.76s-.353-1.06-1.414-2.12c-1.06-1.062-2.121-1.415-2.121-1.415z" fill="#000000"></path></g></svg>
                <svg class="w-6 h-6" fill="#000000" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><g id="SVGRepo_bgCarrier" stroke-width="0"></g><g id="SVGRepo_tracerCarrier" stroke-linecap="round" stroke-linejoin="round"></g><g id="SVGRepo_iconCarrier"><path d="M5.755,20.283,4,8H20L18.245,20.283A2,2,0,0,1,16.265,22H7.735A2,2,0,0,1,5.755,20.283ZM21,4H16V3a1,1,0,0,0-1-1H9A1,1,0,0,0,8,3V4H3A1,1,0,0,0,3,6H21a1,1,0,0,0,0-2Z"></path></g></svg>
            </div>
        </div>
        <p class="pt-4">KaiB did an outstanding job looking after our bunny, Thumper! We were worried about leaving him alone, but Kai's attentive care made all the difference. She came by twice a day for a week, feeding, grooming, and playing with Thumper. Her updates were thorough and filled with cute photos, giving us peace of mind. Kai’s passion for animals is evident in her dedication. We couldn’t have asked for a better pet sitter and will surely book her again in the future!</p>
    </div>
    <div class="w-full shadow-lg border border-gray-300 rounded-md p-2 mt-2">
        <div class="flex justify-between w-full pt-3">
            <div class=" flex w-full gap-3">
                <img src="./img/newspaper-background.png" alt="Article Image" class="rounded-full h-10 w-10">
                <span class="mt-1 text-center items-center">User name  .  22 Jul</span>
            </div>
            <div class="icons flex gap-3 mt-2">
                <svg class="w-6 h-6" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg"><g id="SVGRepo_bgCarrier" stroke-width="0"></g><g id="SVGRepo_tracerCarrier" stroke-linecap="round" stroke-linejoin="round"></g><g id="SVGRepo_iconCarrier"><path fill-rule="evenodd" clip-rule="evenodd" d="m3.99 16.854-1.314 3.504a.75.75 0 0 0 .966.965l3.503-1.314a3 3 0 0 0 1.068-.687L18.36 9.175s-.354-1.061-1.414-2.122c-1.06-1.06-2.122-1.414-2.122-1.414L4.677 15.786a3 3 0 0 0-.687 1.068zm12.249-12.63 1.383-1.383c.248-.248.579-.406.925-.348.487.08 1.232.322 1.934 1.025.703.703.945 1.447 1.025 1.934.058.346-.1.677-.348.925L19.774 7.76s-.353-1.06-1.414-2.12c-1.06-1.062-2.121-1.415-2.121-1.415z" fill="#000000"></path></g></svg>
                <svg class="w-6 h-6" fill="#000000" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><g id="SVGRepo_bgCarrier" stroke-width="0"></g><g id="SVGRepo_tracerCarrier" stroke-linecap="round" stroke-linejoin="round"></g><g id="SVGRepo_iconCarrier"><path d="M5.755,20.283,4,8H20L18.245,20.283A2,2,0,0,1,16.265,22H7.735A2,2,0,0,1,5.755,20.283ZM21,4H16V3a1,1,0,0,0-1-1H9A1,1,0,0,0,8,3V4H3A1,1,0,0,0,3,6H21a1,1,0,0,0,0-2Z"></path></g></svg>
            </div>
        </div>
        <p class="pt-4">KaiB did an outstanding job looking after our bunny, Thumper! We were worried about leaving him alone, but Kai's attentive care made all the difference. She came by twice a day for a week, feeding, grooming, and playing with Thumper. Her updates were thorough and filled with cute photos, giving us peace of mind. Kai’s passion for animals is evident in her dedication. We couldn’t have asked for a better pet sitter and will surely book her again in the future!</p>
    </div>
    </div>

    <div class="flex items-center justify-center border-t border-gray-200 bg-white w-full px-4 py-3 sm:px-6">
        <div class="flex flex-1 justify-between sm:hidden">
            <a href="#" class="relative inline-flex items-center rounded-md border border-gray-300 bg-white px-4 py-2 text-sm font-medium text-gray-700 hover:bg-gray-50">Previous</a>
            <a href="#" class="relative ml-3 inline-flex items-center rounded-md border border-gray-300 bg-white px-4 py-2 text-sm font-medium text-gray-700 hover:bg-gray-50">Next</a>
        </div>
        <div class="hidden sm:flex sm:flex-1 sm:items-center sm:justify-center">

            <div>
                <nav class="isolate inline-flex -space-x-px rounded-md shadow-sm" aria-label="Pagination">
                    <a href="#" class="relative inline-flex items-center rounded-l-md px-2 py-2 text-gray-400 ring-1 ring-inset ring-gray-300 hover:bg-gray-50 focus:z-20 focus:outline-offset-0">
                        <svg class="h-5 w-5 mt-1" viewBox="0 0 20 20" fill="currentColor" aria-hidden="true" data-slot="icon">
                            <path fill-rule="evenodd" d="M11.78 5.22a.75.75 0 0 1 0 1.06L8.06 10l3.72 3.72a.75.75 0 1 1-1.06 1.06l-4.25-4.25a.75.75 0 0 1 0-1.06l4.25-4.25a.75.75 0 0 1 1.06 0Z" clip-rule="evenodd" />
                        </svg>
                        <span class="items-center">Previous</span>

                    </a>
                    <!-- Current: "z-10 bg-indigo-600 text-white focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600", Default: "text-gray-900 ring-1 ring-inset ring-gray-300 hover:bg-gray-50 focus:outline-offset-0" -->
                    <a href="#" aria-current="page" class="relative z-10 inline-flex items-center bg-black px-4 py-2 text-sm font-semibold text-white focus:z-20 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-black-600">1</a>
                    <a href="#" class="relative inline-flex items-center px-4 py-2 text-sm font-semibold text-gray-900 ring-1 ring-inset ring-gray-300 hover:bg-gray-50 focus:z-20 focus:outline-offset-0">2</a>
                    <a href="#" class="relative hidden items-center px-4 py-2 text-sm font-semibold text-gray-900 ring-1 ring-inset ring-gray-300 hover:bg-gray-50 focus:z-20 focus:outline-offset-0 md:inline-flex">3</a>
                    <a href="#" class="relative inline-flex items-center rounded-r-md px-2 py-2 text-gray-400 ring-1 ring-inset ring-gray-300 hover:bg-gray-50 focus:z-20 focus:outline-offset-0">
                        <span class="items-center">Next</span>
                        <svg class="h-5 w-5 mt-1" viewBox="0 0 20 20" fill="currentColor" aria-hidden="true" data-slot="icon">
                            <path fill-rule="evenodd" d="M8.22 5.22a.75.75 0 0 1 1.06 0l4.25 4.25a.75.75 0 0 1 0 1.06l-4.25 4.25a.75.75 0 0 1-1.06-1.06L11.94 10 8.22 6.28a.75.75 0 0 1 0-1.06Z" clip-rule="evenodd" />
                        </svg>
                    </a>
                </nav>
            </div>
        </div>
    </div>





</div>

</body>
</html>
