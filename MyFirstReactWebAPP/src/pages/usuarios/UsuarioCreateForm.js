import React, { useState } from 'react';
import InputMask from 'react-input-mask';

export default function UsuarioCreateForm() {
  const [formData, setFormData] = useState({
    firstName: '',
    lastName: '',
    cpf: '',
    email: '',
    senha: '',
    celular: '',
    cep: '',
    cidade: '',
    estado: '',
    bairro: '',
    endereco: '',
  });

  const [submitStatus, setSubmitStatus] = useState(null);
  const [isAddressEditable, setIsAddressEditable] = useState(false);

  

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));

    if (name === 'cep' && value.length === 9) {
      buscarEnderecoPorCep(value);
    }
  };

  const buscarEnderecoPorCep = (cep) => {
    const sanitizedCep = cep.replace(/\D/g, '');

    fetch(`https://viacep.com.br/ws/${sanitizedCep}/json/`)
      .then((response) => {
        if (!response.ok) throw new Error('Erro ao buscar CEP');
        return response.json();
      })
      .then((data) => {
        if (data.erro) throw new Error('CEP não encontrado');
        setFormData((prev) => ({
          ...prev,
          endereco: data.logradouro || '',
          bairro: data.bairro || '',
          cidade: data.localidade || '',
          estado: data.uf || '',
        }));
        setIsAddressEditable(true);
      })
      .catch((error) => {
        console.error(error);
        setIsAddressEditable(true);
      });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    fetch('http://localhost:3000/usuarios/new', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(formData),
    })
      .then(async (res) => {
        if (!res.ok) {
          // Extrai o JSON do erro
          const errorData = await res.json();
          // Lança erro com a mensagem do backend
          throw new Error(errorData.message || 'Erro ao criar usuário');
        }
        return res.text();
      })
      .then((result) => {
        setSubmitStatus({ type: 'success' });
        console.log(result);
      })
      .catch((err) => {
        setSubmitStatus({ type: 'error', message: err.message });
        console.error(err);
      });
  };


  if (submitStatus === 'success') {
    return (
      <div className="container mt-4">
        <h2>Usuário criado com sucesso</h2>
        <p>O cadastro foi realizado com êxito.</p>
        {/* Opcional: botão para novo cadastro */}
        <button
          className="btn btn-primary"
          onClick={() => {
            setFormData({
              firstName: '',
              lastName: '',
              cpf: '',
              email: '',
              senha: '',
              celular: '',
              cep: '',
              cidade: '',
              estado: '',
              bairro: '',
              endereco: '',
            });
            setIsAddressEditable(false);
            setSubmitStatus(null);
          }}
        >
          Criar Novo Usuário
        </button>
      </div>
    );
  }

  return (
    <div className="container mt-4">
      <h2>Criar Novo Usuário</h2>

      {submitStatus?.type === 'error' && (
        <div className="alert alert-danger">Erro ao criar usuário - {submitStatus.message}</div>
      )}

      <form onSubmit={handleSubmit}>
        {/* Nome e Sobrenome */}
        <div className="mb-3">
          <label className="form-label">Nome</label>
          <input type="text" className="form-control" name="firstName" value={formData.firstName} onChange={handleChange} required />
        </div>

        <div className="mb-3">
          <label className="form-label">Sobrenome</label>
          <input type="text" className="form-control" name="lastName" value={formData.lastName} onChange={handleChange} required />
        </div>

        {/* CPF */}
        <div className="mb-3">
          <label className="form-label">CPF</label>
          <InputMask
            mask="999.999.999-99"
            className="form-control"
            name="cpf"
            value={formData.cpf}
            onChange={handleChange}
            required
          />
        </div>

        {/* Email e Senha */}
        <div className="mb-3">
          <label className="form-label">Email</label>
          <input type="email" className="form-control" name="email" value={formData.email} onChange={handleChange} required />
        </div>

        <div className="mb-3">
          <label className="form-label">Senha</label>
          <input type="password" className="form-control" name="senha" value={formData.senha} onChange={handleChange} required />
        </div>

        {/* Celular e CEP */}
        <div className="mb-3">
          <label className="form-label">Celular</label>
          <InputMask
            mask="(99) 99999-9999"
            className="form-control"
            name="celular"
            value={formData.celular}
            onChange={handleChange}
            required
          />
        </div>

        <div className="mb-3">
          <label className="form-label">CEP</label>
          <InputMask
            mask="99999-999"
            className="form-control"
            name="cep"
            value={formData.cep}
            onChange={handleChange}
            required
          />
        </div>

        {/* Estado */}
        <div className="mb-3">
          <label className="form-label">Estado</label>
          <select
            className="form-control"
            name="estado"
            value={formData.estado}
            onChange={handleChange}
            required
            disabled={!isAddressEditable}
          >
            <option value="">Selecione um estado</option>
            {/* Opções de estados */}
            <option value="AC">Acre</option>
            <option value="AL">Alagoas</option>
            <option value="AP">Amapá</option>
            <option value="AM">Amazonas</option>
            <option value="BA">Bahia</option>
            <option value="CE">Ceará</option>
            <option value="DF">Distrito Federal</option>
            <option value="ES">Espírito Santo</option>
            <option value="GO">Goiás</option>
            <option value="MA">Maranhão</option>
            <option value="MT">Mato Grosso</option>
            <option value="MS">Mato Grosso do Sul</option>
            <option value="MG">Minas Gerais</option>
            <option value="PA">Pará</option>
            <option value="PB">Paraíba</option>
            <option value="PR">Paraná</option>
            <option value="PE">Pernambuco</option>
            <option value="PI">Piauí</option>
            <option value="RJ">Rio de Janeiro</option>
            <option value="RN">Rio Grande do Norte</option>
            <option value="RS">Rio Grande do Sul</option>
            <option value="RO">Rondônia</option>
            <option value="RR">Roraima</option>
            <option value="SC">Santa Catarina</option>
            <option value="SP">São Paulo</option>
            <option value="SE">Sergipe</option>
            <option value="TO">Tocantins</option>
          </select>
        </div>

        {/* Cidade, Bairro, Endereço */}
        <div className="mb-3">
          <label className="form-label">Cidade</label>
          <input
            type="text"
            className="form-control"
            name="cidade"
            value={formData.cidade}
            onChange={handleChange}
            required
            disabled={!isAddressEditable}
          />
        </div>

        <div className="mb-3">
          <label className="form-label">Bairro</label>
          <input
            type="text"
            className="form-control"
            name="bairro"
            value={formData.bairro}
            onChange={handleChange}
            required
            disabled={!isAddressEditable}
          />
        </div>

        <div className="mb-3">
          <label className="form-label">Endereço</label>
          <input
            type="text"
            className="form-control"
            name="endereco"
            value={formData.endereco}
            onChange={handleChange}
            required
            disabled={!isAddressEditable}
          />
        </div>

        {submitStatus?.type === 'error' && (
          <div className="alert alert-danger">Erro ao criar usuário - {submitStatus.message}</div>
        )}


        {/* Botões */}
        <button type="submit" className="btn btn-success">Criar Usuário</button>
        <button
          type="reset"
          className="btn btn-secondary ms-2"
          onClick={() => {
            setFormData({
              firstName: '',
              lastName: '',
              cpf: '',
              email: '',
              senha: '',
              celular: '',
              cep: '',
              cidade: '',
              estado: '',
              bairro: '',
              endereco: '',
            });
            setIsAddressEditable(false);
            setSubmitStatus(null);
          }}
        >
          Limpar Formulário
        </button>

        <div className="mt-3" style={{ height: '2rem' }}></div>
      </form>
    </div>
  );
}
