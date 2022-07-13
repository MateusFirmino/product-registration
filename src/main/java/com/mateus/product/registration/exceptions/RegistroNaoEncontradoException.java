package com.mateus.product.registration.exceptions;

import com.mateus.product.registration.enumarator.AplicacaoMensagemEnum;

public class RegistroNaoEncontradoException extends RuntimeException {
    private static final String NOT_FOUND = AplicacaoMensagemEnum.X0_NAO_ENCONTRADO.trataMensagem("Registro");

    public RegistroNaoEncontradoException() {
        super(NOT_FOUND);
    }

    public RegistroNaoEncontradoException(final String mensagem) {
        super(mensagem);
    }

    public RegistroNaoEncontradoException(final AplicacaoMensagemEnum aplicacaoMensagem, final String... argumentos) {
        super(aplicacaoMensagem.trataMensagem(argumentos));
    }
}
