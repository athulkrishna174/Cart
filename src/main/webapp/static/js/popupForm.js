

document.querySelector(".orderBtn").addEventListener("click", () => {
    document.querySelector("#none").style.display = "block";
    document.querySelector("body").style.overflow = "hidden";
})

document.querySelector(".close_btn").addEventListener("click", () => {
    document.querySelector("#none").style.display = "none";
    document.querySelector("body").style.overflow = "scroll";
})
