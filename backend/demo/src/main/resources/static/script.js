async function enviar() {
    const prompt = document.getElementById("prompt").value;
    const chat = document.getElementById("chat");
    if(!prompt.trim()) return;

    chat.innerHTML += "<div><b>Tú:</b> " + prompt + "</div>";
    document.getElementById("prompt").value = "";

    try {
        const response = await fetch("http://localhost:8081/api/ia/gemini", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ prompt: prompt })
        });

        const data = await response.json();

        // ¡AQUÍ ESTÁ EL TRUCO!
        // Vamos a imprimir la respuesta completa en la consola (F12)
        console.log("Respuesta completa de Google:", data);

        alert("Revisa la consola (F12) para ver el formato real de la respuesta");

    } catch (error) {
        console.error("Error capturado:", error);
    }
}