package com.interfaces.daniel.asoguau.modelo;

import com.interfaces.daniel.asoguau.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hanyou on 17/09/15.
 */
public class Pelicula {

    private String nombre;
    private String descripcion;
    private String clasificacion;
    private List<String> horarios;
    private int idDrawable;


    public static final List<Pelicula> TODAS = new ArrayList<>();
    public static final List<Pelicula> RECIENTES = new ArrayList<>();


    static {


        List<String> horario = new ArrayList<>();
        horario.add("Carabobo - Sambil - 10:00am - 12:00pm");
        horario.add("Carabobo - La Granja - 10:00am - 12:00pm");
        horario.add("Carabobo - Metropolis - 10:00am - 12:00pm");
        horario.add("Caracas - Sambil - 10:00am - 12:00pm");
        horario.add("Zuloa - Sambil - 10:00am - 12:00pm");
        horario.add("Merida - Sambil - 10:00am - 12:00pm");

        TODAS.add(new Pelicula("The Last: Naruto", "Dos años después del fin de la Cuarta Guerra Mundial Shinobi y de la batalla entre Naruto y Sasuke, el mundo vive en completa paz, Pero de pronto la Luna se ve cada vez más cerca de lo normal, lo que genera miedo de que algo malo pase, aun asi las personas siguen con sus vidas. Paralelamente Hanabi Hyuuga es secuestrado por un misterioso hombre en Konoha y Kakashi decide formar un equipo con Naruto, Sakura, Sai, Shikamaru y Hinata para rescatarla.", "B", horario, R.drawable.the_last_naruto_the_movie_2015));
        TODAS.add(new Pelicula("La dama de Negro 2", "En esta segunda etapa, la cinta narra la historia de un grupo de ocho niños evacuados de Londres durante la Segunda Guerra Mundial. Liderados por la directora Jean Hogg y la joven maestra Eve Parkins, llegan a Crythin Gifford, un pueblo rural abandonado. Su destino es la casa Eel Marsh, ahora en ruinas. Mientras buscan refugio lejos del terror de la guerra, de inmediato la presencia de estos nuevos habitantes despierta una fuerza malévola que durante décadas ha mantenido embrujado a este imponente y aislado edificio. De las tinieblas y mientras los chicos juegan, aflora el espíritu vengador, La mujer de negro, que poco a poco irá acabando uno a uno con ellos.", "B", horario, R.drawable.la_dama_de_negro));
        TODAS.add(new Pelicula("A Casa dos Mortos", "El film se centra en un terrible crimen de cómo cinco jovenes estudiantes son brutalmente asesinados en el interior de una abandonada casa. El detective Mark Lewis y la psicóloga del departamento Elizabeth Klein se centran en John Ascot, el principal sospechoso, quien está siendo interrogado en la estación de policía. Durante el interrogatorio explicará lo que ha sucedido con sus amigos, quienes eran cazadores de fantasmas amateurs que estaban realizando una investigación en el interior de la casa. La película nos mostrará imágenes del material encontrado, cintas del interrogatorio así como película clásica.", "B", horario, R.drawable.demonic_poster_italiano));
        TODAS.add(new Pelicula("Eliminado", "Chateando durante una noche, un grupo de seis estudiantes reciben un mensaje en Skype de una compañera de clase que se suicidó un año atrás. De un principio creen que se trata de una broma, pero cuando la chica comienza a revelar oscuros secretos del grupo de amigos, los jóvenes descubren que deberán luchar contra algo de fuera de este mundo, algo que les quiere muertos.", "B", horario, R.drawable.unfriended));
        TODAS.add(new Pelicula("Terremoto", "La falla de San Andrés acaba cediendo ante las temibles fuerzas telúricas y desencadena un terremoto de magnitud 9 en California. El piloto de un helicóptero de búsqueda y rescate (Johnson) y su ex esposa viajan juntos desde Los Ángeles hasta San Francisco para salvar a su única hija. Pero su tortuoso viaje hacia el norte solamente es el comienzo, y cuando piensan que ya ha acabado lo peor todo vuelve a empezar.", "B", horario, R.drawable.terremoto));
        TODAS.add(new Pelicula("El Transportador", "En los bajos fondos de Francia, Frank Martin (Ed Skrein) es conocido como «Transporter», el mejor conductor y mercenario que se puede comprar con dinero. Frank se rige por tres simples reglas: sin nombres, sin preguntas y sin renegociaciones, y transporta cualquier cosa por el precio adecuado. Hasta que conoce a la misteriosa mujer fatal llamada Anna (Loan Chabanol), que lidera un grupo de mortíferos asaltantes y que no se detendrá ante nada con tal de acabar con una despiadada banda rusa de traficantes humanos. Anna sabe que Frank es el mejor en su trabajo y, para asegurarse su colaboración, mantiene rehén a su padre (Ray Stevenson). Ahora, padre e hijo se verán obligados a trabajar con Anna para llevar a esta peligrosa banda ante la justicia.", "B", horario, R.drawable.transportador));
        TODAS.add(new Pelicula("Pixeles", "Unos extraterrestres malinterpretan las imágenes de las máquinas recreativas como una declaración de guerra y deciden atacar la Tierra, empleando dichos juegos como modelos para el asalto. El presidente de EEUU, Will Cooper (Kevin James), recurre entonces a su gran amigo de la infancia y campeón de las maquinitas de los años 80, Sam Brenner (Adam Sandler), quien actualmente trabaja como instalador de sistemas de home cinema, para encabezar un equipo de expertos jugadores de su época (Monaghan, Dinklage y Gad). Su misión será derrotar a los alienígenas y salvar al planeta.", "B", horario, R.drawable.pixels));
        TODAS.add(new Pelicula("Los Vengadores 2", "Cuando Tony Stark intenta reactivar un programa durmiente de mantenimiento de la paz, las cosas se ponen feas y los Héroes Más Poderosos de la Tierra, que incluyen a Iron Man, Capitán América, Thor, El Increíble Hulk, la Viuda Negra y Ojo de Halcón, deberán ponerse a prueba ya que está en juego el destino del planeta. Con la aparición del villano Ultrón, corresponderá a Los Vengadores la tarea de impedir sus terroríficos planes. No tardarán en surgir extrañas alianzas y giros inesperados que poblarán de acción esta epopeya absolutamente única.", "B", horario, R.drawable.los_vengadores_2));
        TODAS.add(new Pelicula("Cinderella", "Cuenta las andanzas de la joven Ella (Lily James) cuyo padre, un comerciante, vuelve a casarse tras la muerte de su madre. Ella quiere dar gusto a su padre y acoge con cariño a su nueva madrastra (Cate Blanchett) y a sus hijas Anastasia (Holliday Grainger) y Drisella (Sophie McShera) en la casa familiar. Pero, cuando el padre de Ella muere inesperadamente, la joven se encuentra a merced de una nueva familia celosa y cruel. Al final, Ella queda relegada a ser una sirvienta cubierta de ceniza por lo que le ponen el triste nombre de Cenicienta. Pero no pierde la esperanza y a pesar de la crueldad con la que la tratan, está dispuesta a cumplir las últimas palabras de su madre que le dijo que debía “ser valiente y amable”. Ella no caerá en la desesperación ni despreciará a los que la maltratan. Y además está ese apuesto extraño que conoce en el bosque. No sabe que se trata de un príncipe; cree que es un aprendiz que trabaja en Palacio y que ha encontrado su alma gemela. Y su suerte está a punto de cambiar cuando envían desde Palacio una invitación abierta a todas las doncellas para que asistan a un baile donde Ella confía en volver a ver al encantador Kit (Richard Madden). Por desgracia, su madrastra le prohíbe asistir y desgarra su vestido en un acto de crueldad. Pero, como en todos los buenos cuentos de hadas, alguien acude en su ayuda. Esta vez toma la forma de una bondadosa mendiga (Helena Bonham-Carter) que, armada con una calabaza y un par de ratones, cambia para siempre la vida de Cenicienta.", "B", horario, R.drawable.cinderella));

        RECIENTES.add(new Pelicula("The Last: Naruto", "Dos años después del fin de la Cuarta Guerra Mundial Shinobi y de la batalla entre Naruto y Sasuke, el mundo vive en completa paz, Pero de pronto la Luna se ve cada vez más cerca de lo normal, lo que genera miedo de que algo malo pase, aun asi las personas siguen con sus vidas. Paralelamente Hanabi Hyuuga es secuestrado por un misterioso hombre en Konoha y Kakashi decide formar un equipo con Naruto, Sakura, Sai, Shikamaru y Hinata para rescatarla.", "B", horario, R.drawable.the_last_naruto_the_movie_2015));
        RECIENTES.add(new Pelicula("La dama de Negro 2", "En esta segunda etapa, la cinta narra la historia de un grupo de ocho niños evacuados de Londres durante la Segunda Guerra Mundial. Liderados por la directora Jean Hogg y la joven maestra Eve Parkins, llegan a Crythin Gifford, un pueblo rural abandonado. Su destino es la casa Eel Marsh, ahora en ruinas. Mientras buscan refugio lejos del terror de la guerra, de inmediato la presencia de estos nuevos habitantes despierta una fuerza malévola que durante décadas ha mantenido embrujado a este imponente y aislado edificio. De las tinieblas y mientras los chicos juegan, aflora el espíritu vengador, La mujer de negro, que poco a poco irá acabando uno a uno con ellos.", "B", horario, R.drawable.la_dama_de_negro));
        RECIENTES.add(new Pelicula("A Casa dos Mortos", "El film se centra en un terrible crimen de cómo cinco jovenes estudiantes son brutalmente asesinados en el interior de una abandonada casa. El detective Mark Lewis y la psicóloga del departamento Elizabeth Klein se centran en John Ascot, el principal sospechoso, quien está siendo interrogado en la estación de policía. Durante el interrogatorio explicará lo que ha sucedido con sus amigos, quienes eran cazadores de fantasmas amateurs que estaban realizando una investigación en el interior de la casa. La película nos mostrará imágenes del material encontrado, cintas del interrogatorio así como película clásica.", "B", horario, R.drawable.demonic_poster_italiano));
        RECIENTES.add(new Pelicula("Eliminado", "Chateando durante una noche, un grupo de seis estudiantes reciben un mensaje en Skype de una compañera de clase que se suicidó un año atrás. De un principio creen que se trata de una broma, pero cuando la chica comienza a revelar oscuros secretos del grupo de amigos, los jóvenes descubren que deberán luchar contra algo de fuera de este mundo, algo que les quiere muertos.", "B", horario, R.drawable.unfriended));
        RECIENTES.add(new Pelicula("Terremoto", "La falla de San Andrés acaba cediendo ante las temibles fuerzas telúricas y desencadena un terremoto de magnitud 9 en California. El piloto de un helicóptero de búsqueda y rescate (Johnson) y su ex esposa viajan juntos desde Los Ángeles hasta San Francisco para salvar a su única hija. Pero su tortuoso viaje hacia el norte solamente es el comienzo, y cuando piensan que ya ha acabado lo peor todo vuelve a empezar.", "B", horario, R.drawable.terremoto));


    }

    public Pelicula(String nombre, String descripcion, String clasificacion, List<String> horarios, int idDrawable) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.clasificacion = clasificacion;
        this.horarios = horarios;
        this.idDrawable = idDrawable;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public List<String> getHorarios() {
        return horarios;
    }

    public int getIdDrawable() {
        return idDrawable;
    }
}
