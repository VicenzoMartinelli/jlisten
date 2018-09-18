
package validator;

import entity.Perfil;

public class ModelValidator {
    public String isValidPerfil(Perfil perfil)
    {
        if(perfil.getNome().isEmpty())
            return "Insira o nome do perfil por gentileza!";
        else if (perfil.getDescricao().isEmpty())
            return "Insira uma descrição para este gênero por gentileza!";
        else if (perfil.getGeneroUm() == null)
        {
            return "Selecione o primeiro gênero para o perfil!\nCaso não existam gêneros importe algumas músicas";
        }
        else if (perfil.getGeneroDois() == null)
        {
            return "Selecione o segundo gênero para o perfil!\nCaso não existam gêneros importe algumas músicas";
        }
        else if (perfil.getGeneroTres() == null)
        {
            return "Selecione o terceiro gênero para o perfil!\nCaso não existam gêneros importe algumas músicas";
        }
        
        return "";
    }
}
