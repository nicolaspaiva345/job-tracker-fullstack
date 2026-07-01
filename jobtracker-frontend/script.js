
const API_URL = "https://jobtracker-backend-743r.onrender.com/api/vagas";

const formVaga = document.getElementById("form-vaga");
const listaVagas = document.getElementById("lista-vagas");

async function buscarVagas() {
    try {
        const resposta = await fetch(API_URL);
        const vagas = await resposta.json();
        
        listaVagas.innerHTML = "";

        if (vagas.length === 0) {
            listaVagas.innerHTML = `<p class="text-gray-500 col-span-2 text-center py-4">Nenhuma vaga cadastrada ainda. Bora aplicar!</p>`;
            return;
        }

        vagas.forEach(vaga => {
            let corStatus = "bg-indigo-900 text-indigo-300 border-indigo-700";
            if (vaga.status === "RECUSADO") corStatus = "bg-red-900 text-red-300 border-red-700";
            if (vaga.status === "ENTREVISTA") corStatus = "bg-green-900 text-green-300 border-green-700";

            const card = `
                <div class="bg-gray-800 p-5 rounded-xl border border-gray-700 shadow-md flex flex-col justify-between relative group">
                    <button onclick="deletarVaga(${vaga.id})" class="absolute top-3 right-3 text-gray-500 hover:text-red-500 transition duration-150 font-bold text-lg">
                        🗑️
                    </button>

                    <div>
                        <div class="flex justify-between items-start mb-2 pr-6">
                            <h3 class="text-lg font-bold text-white tracking-wide">${vaga.cargo}</h3>
                        </div>
                        <p class="text-indigo-400 font-medium text-sm mb-1">🏢 ${vaga.empresa}</p>
                        <p class="text-gray-400 text-xs italic mb-4">${vaga.anotacoes || 'Sem anotações.'}</p>
                    </div>

                    <div class="mt-2 flex flex-col gap-2">
                        <div class="flex items-center justify-between bg-gray-700/50 p-2 rounded-lg border border-gray-600">
                            <span class="text-xs text-gray-400 font-medium">Status:</span>
                            <select onchange="alterarStatusVaga(${vaga.id}, this.value)" class="bg-gray-700 text-xs font-semibold rounded px-2 py-1 focus:outline-none cursor-pointer text-white">
                                <option value="INSCRITO" ${vaga.status === 'INSCRITO' ? 'selected' : ''}>Inscrito</option>
                                <option value="ENTREVISTA" ${vaga.status === 'ENTREVISTA' ? 'selected' : ''}>Entrevista</option>
                                <option value="RECUSADO" ${vaga.status === 'RECUSADO' ? 'selected' : ''}>Recusado</option>
                            </select>
                        </div>

                        <a href="${vaga.linkVaga}" target="_blank" class="text-center bg-gray-700 hover:bg-gray-600 text-gray-200 text-xs font-semibold py-2 px-4 rounded-lg border border-gray-600 transition duration-200">
                            Ver Anúncio da Vaga 🔗
                        </a>
                    </div>
                </div>
            `;
            listaVagas.innerHTML += card;
        });
    } catch (error) {
        console.error("Erro ao buscar vagas:", error);
    }
}

async function alterarStatusVaga(id, novoStatus) {
    try {
        const resposta = await fetch(`${API_URL}/${id}/status`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(novoStatus)
        });

        if (resposta.ok) {
            buscarVagas(); // Atualiza o visual para aplicar as novas cores
        } else {
            alert("Erro ao atualizar status.");
        }
    } catch (error) {
        console.error("Erro:", error);
    }
}

async function deletarVaga(id) {
    if (confirm("Deseja realmente excluir esta candidatura?")) {
        try {
            const resposta = await fetch(`${API_URL}/${id}`, {
                method: "DELETE"
            });

            if (resposta.ok) {
                buscarVagas(); // Remove o card da tela instantaneamente
            } else {
                alert("Erro ao deletar vaga.");
            }
        } catch (error) {
            console.error("Erro:", error);
        }
    }
}
formVaga.addEventListener("submit", async (event) => {
    event.preventDefault(); // Impede a página de recarregar ao clicar no botão

    const novaVaga = {
        empresa: document.getElementById("empresa").value,
        cargo: document.getElementById("cargo").value,
        linkVaga: document.getElementById("linkVaga").value,
        anotacoes: document.getElementById("anotacoes").value,
        status: document.getElementById("status").value
    };

    try {
        const resposta = await fetch(API_URL, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(novaVaga)
        });

        if (resposta.ok) {
            formVaga.reset(); // Limpa as caixas de texto do formulário
            buscarVagas();    // Atualiza a lista na tela imediatamente!
        } else {
            alert("Erro ao salvar a vaga. Verifique o console do IntelliJ.");
        }
    } catch (error) {
        console.error("Erro ao cadastrar vaga:", error);
    }
});

buscarVagas();