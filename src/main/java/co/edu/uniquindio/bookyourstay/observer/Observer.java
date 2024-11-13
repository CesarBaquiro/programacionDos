package co.edu.uniquindio.bookyourstay.observer;

public interface Observer {
    void notificar();


    default boolean verifyActivationCode(String codeInput, String generatedCode) {
        return codeInput.equals(generatedCode);
    }

    // Método para enviar el código a un controlador
    default void storeReceivedCode(String code){};
}
