package hello.core.singleton;

public class SingletonService {

    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance() {
        return instance;
    }
    public void logic(){
        System.out.println("instance = " + instance);
    }
    private SingletonService(){}

    public static void main(String[] args) {
        SingletonService singletonService = new SingletonService();

    }
}
