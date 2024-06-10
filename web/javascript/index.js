/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */


document.addEventListener('DOMContentLoaded', function () {
    // Function to initialize carousel controls for a given carousel
    function initializeCarousel(carouselId) {
        var carousel = document.querySelector(`#${carouselId}`);
        var carouselInner = carousel.querySelector('.carousel-inner');
        var cardWidth = carousel.querySelector('.carousel-item').offsetWidth;

        carousel.querySelector('.carousel-control-next').addEventListener('click', function () {
            carouselInner.appendChild(carouselInner.firstElementChild);
            carouselInner.scrollBy({ left: cardWidth, behavior: 'smooth' });
        });

        carousel.querySelector('.carousel-control-prev').addEventListener('click', function () {
            carouselInner.prepend(carouselInner.lastElementChild);
            carouselInner.scrollBy({ left: -cardWidth, behavior: 'smooth' });
        });
    }

    // Initialize carousels by their unique IDs
    initializeCarousel('newProductsCarousel1');
    initializeCarousel('newProductsCarousel2');
    initializeCarousel('newProductsCarousel3');
    initializeCarousel('newProductsCarousel4');
});

