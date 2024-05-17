
document.addEventListener("DOMContentLoaded", function() {
    const horarioInput = document.getElementById("horarioInput");

    horarioInput.addEventListener("input", function() {
        const horarioValue = this.value;
        const partesHorario = horarioValue.split(":");
        const minutos = parseInt(partesHorario[1]);

        // Se os minutos não forem 00 ou 30, ajuste o horário
        if (minutos % 30 !== 0) {
            const novoMinuto = Math.floor(minutos / 30) * 30; // Arredonda para baixo para o próximo múltiplo de 30
            const novoHorario = partesHorario[0] + ":" + (novoMinuto < 10 ? "0" : "") + novoMinuto;
            this.value = novoHorario;
        }
        
    });
});

