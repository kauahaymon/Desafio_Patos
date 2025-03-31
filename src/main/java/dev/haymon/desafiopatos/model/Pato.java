package dev.haymon.desafiopatos.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

import static dev.haymon.desafiopatos.model.enums.PrecoPatoEnum.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "patos")
public class Pato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @ManyToOne
    @JoinColumn(name = "id_mae")
    private Pato mae;
    private boolean vendido;

    @OneToMany(mappedBy = "mae", cascade = CascadeType.ALL)
    private List<Pato> filhos;

    public BigDecimal calcularPreco() {
        BigDecimal preco;
        int numeroDeFilhos = filhos != null ? filhos.size() : 0;

        if (numeroDeFilhos == 1) {
            preco = BigDecimal.valueOf(PRECO_COM_1_FILHO.getValor());
        } else if (numeroDeFilhos == 2) {
            preco = BigDecimal.valueOf(PRECO_COM_2_FILHOS.getValor());
        } else {
            preco = BigDecimal.valueOf(PRECO_SEM_FILHOS.getValor());
        }
        return preco;
    }
}
