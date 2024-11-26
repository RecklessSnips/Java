package Java8.generics.DAO;

public class JediDAO extends DAO<Jedi>{

    public static void main(String[] args) {
        JediDAO jediDAO = new JediDAO();
        // 那么此时的 DAO 只能接受 Jedi，同理，别的class继承DAO，可以写他们想要的泛型
        jediDAO.add(new Jedi());
    }
}
