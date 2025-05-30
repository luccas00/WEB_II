package br.ufop.edu.web2.ticket.user.service;

import br.ufop.edu.web2.ticket.user.converter.UserConverter;
import br.ufop.edu.web2.ticket.user.dtos.UserRecordDTO;
import br.ufop.edu.web2.ticket.user.models.UserModel;
import br.ufop.edu.web2.ticket.user.repositories.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private IUserRepository userRepository;

    public List<UserRecordDTO> getAllUsers() {

        List<UserModel> result = userRepository.findAll();

        return result.stream().map(UserConverter::toUserRecordDTO).toList();

    }

    public List<UserRecordDTO> getAllUsers1() {

        List<UserModel> result = userRepository.findAll();
        List<UserRecordDTO> dtos = new ArrayList<UserRecordDTO>();

        for (UserModel user : result) {
            dtos.add(UserConverter.toUserRecordDTO(user));
        }

        return dtos;

    }
}
