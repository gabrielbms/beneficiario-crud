package beneficiario.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Beneficiario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataInclusao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataAtualizacao;
    
//    @OneToMany(mappedBy = "beneficiario", cascade = CascadeType.ALL, orphanRemoval = true)
//    @OneToMany(mappedBy = "beneficiario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OneToMany(mappedBy = "beneficiario", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Documento> documentos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public LocalDate getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(LocalDate dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public LocalDate getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(LocalDate dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public List<Documento> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<Documento> documentos) {
		this.documentos = documentos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(documentos, id, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Beneficiario other = (Beneficiario) obj;
		return Objects.equals(documentos, other.documentos) && Objects.equals(id, other.id)
				&& Objects.equals(nome, other.nome);
	}
}