package edu.ifmt.cobrancaifmt2.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// Entidade JPA que representa um titulo e sera mapeada para uma tabela no banco.
@Entity
public class Titulo {

    // Chave primaria gerada automaticamente pelo banco de dados.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    private String descricao;

    // Converte a data recebida do formulario, como 22/01/2020, para LocalDate.
    // LocalDate ja e salvo como DATE pelo JPA, por isso nao usa @Temporal.
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataVencimento;

    // Converte valores no formato brasileiro, como 123,50, para BigDecimal.
    @NumberFormat(pattern = "#,##0.00")
    private BigDecimal valor;

    // Salva o nome do enum no banco, como PENDENTE ou RECEBIDO.
    @Enumerated(EnumType.STRING)
    private StatusTitulo status;

    // Opcoes permitidas para o status de um titulo.
    public enum StatusTitulo {
        PENDENTE("Pendente"), 
        RECEBIDO("Recebido");

        private String descricao;

        StatusTitulo(String descricao) {
            this.descricao = descricao;
        }
    }
    public Long getCodigo() {
        return codigo;
    }
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public LocalDate getDataVencimento() {
        return dataVencimento;
    }
    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }
    public BigDecimal getValor() {
        return valor;
    }
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
    public StatusTitulo getStatus() {
        return status;
    }
    public void setStatus(StatusTitulo status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
        return result;
    }

    // Dois titulos sao considerados iguais quando possuem o mesmo codigo.
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Titulo other = (Titulo) obj;
        if (codigo == null) {
            return other.codigo == null;
        }
        return codigo.equals(other.codigo);
    }
}
