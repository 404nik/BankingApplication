<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome</title>
    <link rel="icon" type="image/x-icon" href="https://t4.ftcdn.net/jpg/00/61/06/27/360_F_61062796_NF87GPnWV0fQ2LhoYNlyjev0PocRwZj9.jpg">
    <style>
        body, html {
            height: 100%;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
        }

        /* Header styling */
        header {
            background-color: #2c3e50;
            padding: 10px 20px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            flex-shrink: 0;
        }

        header h1 {
            color: white;
            font-size: 28px;
            margin: 0;
            text-align: left;
        }

        /* Buttons container on the right side */
        .header-buttons {
            display: flex;
            gap: 15px; /* Space between buttons */
        }

        .header-buttons a {
            display: inline-block;
            padding: 12px 25px;
            text-decoration: none;
            font-size: 18px;
            color: white;
            background-image: linear-gradient(45deg, #1b2e3a, #09283d); /* Gradient button */
            border-radius: 8px;
            transition: all 0.3s ease;
            box-shadow: 0 5px 15px rgba(52, 152, 219, 0.4);
        }

        .header-buttons a:hover {
            background-image: linear-gradient(45deg, #1a3241, #06273c); /* Reverse gradient on hover */
            transform: translateY(-5px);
            box-shadow: 0 8px 20px rgba(52, 152, 219, 0.6);
        }

        /* Carousel styling */
        .carousel {
            flex: 1; /* Takes up the remaining space */
        }

        .carousel-inner {
            height: calc(100vh - 60px - 40px); /* Adjust based on the header and footer height */
        }

        .carousel-item {
            height: 100%;
        }

        .carousel-image {
            height: 100%;
            object-fit: cover; /* Ensure the image covers the area */
        }

        /* Footer styling */
        footer {
            background-color: #2c3e50;
            color: white;
            padding: 10px 0;
            text-align: center;
            flex-shrink: 0;
        }

        .footer-content {
            display: flex;
            justify-content: space-around;
            flex-wrap: wrap;
            gap: 20px;
        }

        .footer-content div {
            flex: 1;
            min-width: 150px;
        }

        .footer-content h3 {
            color: #ecf0f1;
            font-size: 18px;
        }

        .footer-content p, .footer-content a {
            color: #bdc3c7;
            font-size: 14px;
            margin: 5px 0;
            text-decoration: none;
        }

        .footer-content a:hover {
            text-decoration: underline;
        }

        .footer-content .policies {
            display: flex;
            gap: 50px; /* Spacing between links */
        }

        /* Responsive Design */
        @media (max-width: 768px) {
            .header-buttons a {
                font-size: 16px;
                padding: 10px 20px;
            }

            header h1 {
                font-size: 22px;
            }

            .carousel-image {
                height: auto; /* Allow image to resize for smaller screens */
            }
        }
    </style>
</head>
<body>

    <!-- Header -->
    <header>
        <h1>INB STATE BANK</h1>
        <div class="header-buttons">
            <a href="/app/prelogin">Admin</a>
            <a href="/app/precustomerlogin">Customer</a>
        </div>
    </header>

    <!-- Carousel -->
    <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
            <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
        </ol>
        <div class="carousel-inner">
            <div class="carousel-item active">
                <video class="d-block w-100 carousel-image" muted disablePictureInPicture>
                    <source src="/images/FirstSlide.mp4" type="video/mp4">
                    Your browser does not support the video tag.
                </video>
            </div>
            <div class="carousel-item">
                <video class="d-block w-100 carousel-image" muted disablePictureInPicture>
                    <source src="/images/SecondSlide.mp4" type="video/mp4">
                    Your browser does not support the video tag.
                </video>
            </div>
            <div class="carousel-item">
                <video class="d-block w-100 carousel-image" muted disablePictureInPicture>
                    <source src="/images/ThirdSlide.mp4" type="video/mp4">
                    Your browser does not support the video tag.
                </video>
            </div>
        </div>
        <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
    

    <!-- Footer -->
    <footer>
        <div class="footer-content">
            <p>&copy; 2024 INB STATE BANK. All rights reserved.</p>
        </div>
    </footer>

    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"></script>
    <script>
        // Add event listener for when the carousel slide is changed
        $('#carouselExampleIndicators').on('slid.bs.carousel', function () {
            // Pause all videos
            $('video').each(function() {
                this.pause();
            });
            
            // Autoplay the video on the active slide
            var activeSlide = $('.carousel-item.active video')[0];
            if (activeSlide) {
                activeSlide.play();
            }
        });
    
        // Automatically play the video on the first slide when the page loads
        $(document).ready(function() {
            var firstSlideVideo = $('.carousel-item.active video')[0];
            if (firstSlideVideo) {
                firstSlideVideo.play();
            }
        });
    </script>
</body>
</html>
