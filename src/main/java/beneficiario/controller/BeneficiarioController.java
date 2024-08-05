package beneficiario.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import beneficiario.entity.Beneficiario;
import beneficiario.entity.Documento;
import beneficiario.service.BeneficiarioService;

@RestController
@RequestMapping("/api/beneficiarios")
public class BeneficiarioController {

    @Autowired
    private BeneficiarioService beneficiarioService;

    @PostMapping
    public ResponseEntity<Beneficiario> createBeneficiario(@RequestBody Beneficiario beneficiario) {
        Beneficiario novoBeneficiario = beneficiarioService.salvar(beneficiario);
        return ResponseEntity.ok(novoBeneficiario);
    }

    @GetMapping
    public ResponseEntity<List<Beneficiario>> listarTodos() {
        List<Beneficiario> beneficiarios = beneficiarioService.listarTodos();
        return ResponseEntity.ok(beneficiarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Beneficiario> obterBeneficiarioPorId(@PathVariable Long id) {
        Optional<Beneficiario> beneficiario = beneficiarioService.obterPorId(id);
        return beneficiario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @GetMapping("/{id}/documentos")
    public ResponseEntity<List<Documento>> obterDocumentosPorIdBeneficiario(@PathVariable Long id) {
        List<Documento> documentos = beneficiarioService.obterDocumentosPorIdBeneficiario(id);
        return documentos.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(documentos);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Beneficiario> atualizarBeneficiario(@PathVariable Long id, @RequestBody Beneficiario beneficiario) {
        beneficiario.setId(id);
        Beneficiario beneficiarioAtualizado = beneficiarioService.atualizar(beneficiario);
        return ResponseEntity.ok(beneficiarioAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerBeneficiario(@PathVariable Long id) {
        beneficiarioService.remover(id);
        return ResponseEntity.noContent().build();
    }
}