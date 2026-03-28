package com.glop.cibl_orga_sport.service;

import com.glop.cibl_orga_sport.data.Sportif;
import com.glop.cibl_orga_sport.data.Utilisateur;
import com.glop.cibl_orga_sport.data.Visiteur;
import com.glop.cibl_orga_sport.data.Commissaire;
import com.glop.cibl_orga_sport.data.enumType.DocumentStatusEnum;
import com.glop.cibl_orga_sport.data.Lieu;
import com.glop.cibl_orga_sport.data.ParticipantSportif;
import com.glop.cibl_orga_sport.data.UserDtoJson;
import com.glop.cibl_orga_sport.dto.SportifDTO;
import com.glop.cibl_orga_sport.dto.VisiteurDTO;
import com.glop.cibl_orga_sport.dto.UtilisateurDTO;
import com.glop.cibl_orga_sport.dto.RolesDto;
import com.glop.cibl_orga_sport.repository.UtilisateurRepository;
import com.glop.cibl_orga_sport.repository.LieuRepository;
import com.glop.cibl_orga_sport.repository.SportifRepository;
import com.glop.cibl_orga_sport.repository.ParticipantSportifRepository;
import com.glop.cibl_orga_sport.mapper.SportifMapper;
import com.glop.cibl_orga_sport.mapper.VisiteurMapper;
import com.glop.cibl_orga_sport.exception.EntityNotFoundException;
import com.glop.cibl_orga_sport.exception.ErrorCodes;
import com.glop.cibl_orga_sport.mapper.UtilisateurMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    @Autowired
    private UtilisateurRepository repository;

    @Autowired
    private SportifRepository sportifRepository;

    @Autowired
    private ParticipantSportifRepository participantSportifRepository;

    @Autowired
    private LieuRepository lieuRepository;

    @Autowired
    private HistoryService historyService;

    @Override
    public Sportif createSportif(SportifDTO dto) {
        Sportif sportif = SportifMapper.toEntity(dto);
        if (dto.getLieu() != null && dto.getLieu().getIdLieu() != null) {
            Optional<Lieu> lieu = lieuRepository.findById(dto.getLieu().getIdLieu());
            lieu.ifPresent(sportif::setLieu);
        }
        Sportif savedSportif = repository.save(sportif);
        ParticipantSportif participantSportif = new ParticipantSportif(savedSportif);
        participantSportifRepository.save(participantSportif);
        return savedSportif;
    }

    @Override
    public Visiteur createVisiteur(VisiteurDTO dto) {
        Visiteur visiteur = VisiteurMapper.toEntity(dto);
        if (dto.getLieu() != null && dto.getLieu().getIdLieu() != null) {
            Optional<Lieu> lieu = lieuRepository.findById(dto.getLieu().getIdLieu());
            lieu.ifPresent(visiteur::setLieu);
        }
        return repository.save(visiteur);
    }

    @Override
    public List<Sportif> getAllSportifs() {
        return repository.findAllSportifs();
    }

    @Override
    public List<Visiteur> getAllVisiteurs() {
        return repository.findAllVisiteurs();
    }

    @Override
    public List<Commissaire> getAllCommissaires() {
        return repository.findAllCommissaires();
    }

    @Override
    public List<Sportif> searchSportifs(String query) {
        return sportifRepository.findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCaseOrEmailContainingIgnoreCase(
                query, query, query);
    }

    @Override
    public List<ParticipantSportif> searchParticipantSportifs(String query) {
        return participantSportifRepository.searchParticipantSportifs(query);
    } 


    @Override
    public UtilisateurDTO findByEmail(String email) {
        if (email == null) {
            log.error("Dear customer, login is null");
            return null;
        }
        return repository.findByEmail(email)
                .map(utilisateur -> UtilisateurDTO.fromEntity(utilisateur))  // ✅ Lambda
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucun utilisateur avec l'email = " + email + " n'a été trouvé dans la BDD",
                        ErrorCodes.USER_NOT_FOUND));
    }

    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return repository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }

    @Override
    public UtilisateurDTO findById(Long id) {
        return repository.findById(id)
                .map(UtilisateurMapper::toSpecificDTO)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucun utilisateur avec l'id = " + id + " n'a été trouvé dans la BDD",
                        ErrorCodes.USER_NOT_FOUND));
    }

    @Override
    public UserDtoJson updateNoMdp(Long id, UserDtoJson userDto) {
        log.info("Inside update roles{}", id);
        Optional<Utilisateur> userOptional = repository.findById(id);

        if (userOptional.isPresent()) {
            log.info("User found with id {}", id);
            Utilisateur user = userOptional.get();

            String existingPassword = user.getMdp();

            user.setNom(userDto.getLastname());
            user.setPrenom(userDto.getName());
            user.setEmail(userDto.getLogin());
            user.setState(userDto.getState());
            user.setRoles(RolesDto.toEntity(userDto.getRoles()));
            user.setMdp(existingPassword);

            Utilisateur savedUser = repository.save(user);
            UtilisateurDTO modifiedUserDto = UtilisateurDTO.fromEntity(savedUser);
            UserDtoJson userDtoJson = convertToUserDtoJson(modifiedUserDto);
            historyService.saveHistory("modify User", "success", savedUser);

            return userDtoJson;
        } else {
            log.info("User with id {} not found", id);
            historyService.saveHistory("modify User", "failed");
            return null;
        }
    }

    @Override
    public UserDtoJson approval(Long id) {
        log.info("Inside update roles{}", id);
        Optional<Utilisateur> userOptional = repository.findById(id);

        if (userOptional.isPresent()) {
            log.info("User found with id {}", id);
            Utilisateur user = userOptional.get();
            user.setState(10);
            Utilisateur savedUser = repository.save(user);
            UtilisateurDTO modifiedUserDto = UtilisateurDTO.fromEntity(savedUser);
            UserDtoJson userDtoJson = convertToUserDtoJson(modifiedUserDto);
            historyService.saveHistory("Approaval User", "success", savedUser);

            return userDtoJson;
        } else {
            log.info("User with id {} not found", id);
            historyService.saveHistory("Approaval User", "failed");
            return null;
        }
    }

    @Override
    public List<UserDtoJson> findAll() {
        return repository.findAll().stream()
                .map(user -> convertToUserDtoJson(UtilisateurDTO.fromEntity(user)))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateCertificatMedical(Long id, byte[] content) {
        log.info("Updating medical certificate for athlete {}", id);
        sportifRepository.findById(id).ifPresent(sportif -> {
            sportif.setCertificatMedicalContenu(content);
            sportif.setaFounicertificatMedical(true);
            sportif.setCertificatMedicalStatus(DocumentStatusEnum.EN_ATTENTE_DE_VALIDATION);
            Sportif saved = sportifRepository.save(sportif);
            System.out.println("Certificat médical enregistré pour le sportif " + id + " (taille: " + content.length + " octets). Status: EN_ATTENTE");
            historyService.saveHistory("upload medical certificate", "success", saved);
        });
    }

    @Override
    @Transactional
    public void updatePasseport(Long id, byte[] content) {
        log.info("Updating passport for athlete {}", id);
        sportifRepository.findById(id).ifPresent(sportif -> {
            sportif.setPasseportContenu(content);
            sportif.setaFouniPasseport(true);
            sportif.setPasseportStatus(DocumentStatusEnum.EN_ATTENTE_DE_VALIDATION);
            Sportif saved = sportifRepository.save(sportif);
            System.out.println("Passeport enregistré pour le sportif " + id + " (taille: " + content.length + " octets). Status: EN_ATTENTE");
            historyService.saveHistory("upload passport", "success", saved);
        });
    }

    @Override
    @Transactional
    public void updateCharteConformite(Long id, boolean value) {
        log.info("Updating charter compliance for athlete {}", id);
        sportifRepository.findById(id).ifPresent(sportif -> {
            sportif.setEstConformeCharteEuropeenne(value);
            Sportif saved = sportifRepository.save(sportif);
            System.out.println("Conformité charte mise à jour pour le sportif " + id + " : " + value);
            historyService.saveHistory("update charter compliance", "success", saved);
        });
    }

    @Override
    @Transactional
    public void updateCertificatMedicalStatus(Long id, DocumentStatusEnum status) {
        log.info("Updating medical certificate status for athlete {}", id);
        sportifRepository.findById(id).ifPresent(sportif -> {
            sportif.setCertificatMedicalStatus(status);
            if (status == DocumentStatusEnum.REFUSE) {
                sportif.setaFounicertificatMedical(false);
            }
            Sportif saved = sportifRepository.save(sportif);
            System.out.println("Statut certificat médical mis à jour pour le sportif " + id + " : " + status);
            historyService.saveHistory("update medical cert status", "success", saved);
        });
    }

    @Override
    @Transactional
    public void updatePasseportStatus(Long id, DocumentStatusEnum status) {
        log.info("Updating passport status for athlete {}", id);
        sportifRepository.findById(id).ifPresent(sportif -> {
            sportif.setPasseportStatus(status);
            if (status == DocumentStatusEnum.REFUSE) {
                sportif.setaFouniPasseport(false);
            }
            Sportif saved = sportifRepository.save(sportif);
            System.out.println("Statut passeport mis à jour pour le sportif " + id + " : " + status);
            historyService.saveHistory("update passport status", "success", saved);
        });
    }

    private UserDtoJson convertToUserDtoJson(UtilisateurDTO dto) {
        UserDtoJson json = new UserDtoJson();
        json.setLastname(dto.getNom());
        json.setName(dto.getPrenom());
        json.setLogin(dto.getEmail());
        json.setState(dto.getState());
        json.setRoles(dto.getRoles());
        return json;
    }
}