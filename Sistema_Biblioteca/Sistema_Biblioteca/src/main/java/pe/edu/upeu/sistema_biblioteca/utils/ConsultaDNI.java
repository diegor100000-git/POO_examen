package pe.edu.upeu.sistema_biblioteca.utils;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pe.edu.upeu.sistema_biblioteca.dto.PersonaDto;

import java.io.IOException;

public abstract class ConsultaDNI {

    public PersonaDto consultarDNI (String dni){
        PersonaDto personaDto=new PersonaDto();
         String url = "https://eldni.com/pe/buscar-datos-por-dni";
         try {
            Connection.Response getResponse = Jsoup.connect(url)
                    .method(Connection.Method.GET)
                    .execute();

            Document doc = getResponse.parse();
            String token = doc.select("input[name=_token]").attr("value");


            Connection.Response postResponse = Jsoup.connect(url)
                    .cookies(getResponse.cookies())
                    .data("_token", token)
                    .data("dni", dni)
                    .method(Connection.Method.POST)
                    .ignoreContentType(true)
                    .execute();

            Document resultDoc = Jsoup.parse(postResponse.body());
            Element fila = resultDoc.select("table tbody tr").first();
             if (fila != null) {
                 Elements celdas = fila.select("td");
                 personaDto.setDni(celdas.get(0).text());
                 personaDto.setNombre(celdas.get(1).text());
                 personaDto.setApellidoPaterno(celdas.get(2).text());
                 personaDto.setApellidoMaterno(celdas.get(3).text());
             }
            }catch(IOException e){
                e.printStackTrace();
            }
            return personaDto;
        }
}

