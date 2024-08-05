package beneficiario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import beneficiario.entity.Beneficiario;
import beneficiario.entity.Documento;
import beneficiario.repository.BeneficiarioRepository;
import beneficiario.repository.DocumentoRepository;

@Service
public class BeneficiarioService {

	@Autowired
	private BeneficiarioRepository beneficiarioRepository;

	@Autowired
	private DocumentoRepository documentoRepository;

	public Beneficiario salvar(Beneficiario beneficiario) {
		if (beneficiario.getDocumentos() != null) {
			for (Documento documento : beneficiario.getDocumentos()) {
				documento.setBeneficiario(beneficiario);
			}
		}
		return beneficiarioRepository.save(beneficiario);
	}

	public Beneficiario atualizar(Beneficiario beneficiario) {
		Beneficiario existingBeneficiario = obterPorId(beneficiario.getId())
				.orElseThrow(() -> new RuntimeException("Beneficiário não encontrado"));

		// Atualiza os atributos do beneficiário
		existingBeneficiario.setNome(beneficiario.getNome());
		existingBeneficiario.setTelefone(beneficiario.getTelefone());
		existingBeneficiario.setDataNascimento(beneficiario.getDataNascimento());
		existingBeneficiario.setDataAtualizacao(beneficiario.getDataAtualizacao());

		// Atualiza ou adiciona documentos
		if (beneficiario.getDocumentos() != null) {
			for (Documento documento : beneficiario.getDocumentos()) {
				if (documento.getId() != null) {
					// Atualiza o documento existente
					Documento existingDocumento = documentoRepository.findById(documento.getId())
							.orElseThrow(() -> new RuntimeException("Documento não encontrado"));
					existingDocumento.setTipoDocumento(documento.getTipoDocumento());
					existingDocumento.setDescricao(documento.getDescricao());
					existingDocumento.setDataInclusao(documento.getDataInclusao());
					existingDocumento.setDataAtualizacao(documento.getDataAtualizacao());
					existingDocumento.setBeneficiario(existingBeneficiario);
				} else {
					// Adiciona novo documento
					documento.setBeneficiario(existingBeneficiario);
				}
			}
		}

		return beneficiarioRepository.save(existingBeneficiario);
	}

	public List<Beneficiario> listarTodos() {
		return beneficiarioRepository.findAll();
	}

	public Optional<Beneficiario> obterPorId(Long id) {
		return beneficiarioRepository.findById(id);
	}

	public List<Documento> obterDocumentosPorIdBeneficiario(Long beneficiarioId) {
		return documentoRepository.findByBeneficiarioId(beneficiarioId);
	}

	public void remover(Long id) {
		beneficiarioRepository.deleteById(id);
	}
}