package dev.haymon.desafiopatos.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "venda_pato")
public class VendaPato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_venda")
    private Venda venda;
    @OneToOne
    @JoinColumn(name = "id_pato", unique = true)
    private Pato pato;
}
