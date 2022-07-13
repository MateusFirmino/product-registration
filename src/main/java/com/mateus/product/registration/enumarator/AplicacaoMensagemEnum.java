package com.mateus.product.registration.enumarator;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public enum AplicacaoMensagemEnum {

    X0_NAO_ENCONTRADO("x0.naoEncontrado");

    private final String valor;

    AplicacaoMensagemEnum(final String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return this.valor;
    }

    public String trataMensagem(final String... args) {
        return this.trataMensagem((Object[]) args);
    }

    public String trataMensagem(final Object... args) {
        final ResourceBundle bundle = ResourceBundle.getBundle("messages", Locale.getDefault());
        final String mensagem = bundle.getString(this.valor);
        return MessageFormat.format(mensagem, args);
    }
}
