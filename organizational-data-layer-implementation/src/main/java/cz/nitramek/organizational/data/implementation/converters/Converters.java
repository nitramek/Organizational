package cz.nitramek.organizational.data.implementation.converters;

import cz.nitramek.organizational.data.implementation.dto.MessageDTO;
import cz.nitramek.organizational.data.implementation.dto.PermissionDTO;
import cz.nitramek.organizational.data.implementation.dto.RoleDTO;
import cz.nitramek.organizational.data.implementation.dto.UserDTO;
import cz.nitramek.organizational.domain.classes.Message;
import cz.nitramek.organizational.domain.classes.Permission;
import cz.nitramek.organizational.domain.classes.Role;
import cz.nitramek.organizational.domain.classes.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

public class Converters {
    public static User createUser(UserDTO userDTO) {
        User u = new User();
        u.setId(userDTO.getId());
        u.setNickname(userDTO.getNickname());
        u.setFirstName(userDTO.getFirstName());
        u.setLastName(userDTO.getLastName());
        u.setPassword(userDTO.getPassword());
        u.setEmail(userDTO.getEmail());
        u.setBirthday(userDTO.getBirthday());
        u.setAdministrator(userDTO.isAdministrator());
        u.setRoles(
                userDTO.getRoles().stream().
                        map(Converters::createRole).
                               collect(Collectors.toCollection(ArrayList<Role>::new))
                  );
        u.setSent(
                userDTO.getSent().stream().
                        map(Converters::createMessage).
                               collect(Collectors.toCollection(ArrayList<Message>::new))
                 );
        u.setReceived(userDTO.getReceived().stream().
                              map(Converters::createMessage).
                                     collect(Collectors.toCollection(ArrayList<Message>::new))
                     );
        return u;
    }

    public static Message createMessage(MessageDTO messageDTO) {
        Message m = new Message(messageDTO.getSubject());
        m.setId(messageDTO.getId());
        m.setDateSend(messageDTO.getDateSend());
        m.setText(messageDTO.getText());
        return m;
    }

    public static MessageDTO createMessage(Message message) {
        MessageDTO m = new MessageDTO();
        m.setSubject(message.getSubject());
        m.setId(message.getId());
        m.setDateSend(message.getDateSend());
        m.setText(message.getText());
        return m;
    }

    public static Role createRole(RoleDTO roleDTO) {
        Role r = new Role();
        r.setId(roleDTO.getId());
        r.setName(roleDTO.getName());
        r.setDisplayName(roleDTO.getDisplayName());
        r.setPermissions(
                roleDTO.getPermission().stream().
                        map(p -> Converters.createPermission(p)).
                               collect(Collectors.toCollection(ArrayList<Permission>::new))
                        );
        return r;
    }

    public static RoleDTO createRole(Role role) {
        RoleDTO r = new RoleDTO();
        r.setId(role.getId());
        r.setName(role.getName());
        r.setDisplayName(role.getDisplayName());
        r.setPermissions(
                role.getPermissions().stream().
                        map(Converters::createPermission).
                            collect(Collectors.toCollection(HashSet<PermissionDTO>::new))
                        );
        return r;
    }

    private static PermissionDTO createPermission(Permission permission) {
        PermissionDTO p = new PermissionDTO();
        p.setId(permission.getId());
        p.setLevel(permission.getLevel());
        return p;
    }


    private static Permission createPermission(PermissionDTO permissionDTO) {
        Permission p = new Permission(permissionDTO.getLevel());
        p.setId(permissionDTO.getId());
        p.setLevel(permissionDTO.getLevel());
        return p;
    }

    public static UserDTO createUser(User user) {
        UserDTO u = new UserDTO();
        u.setId(user.getId());
        u.setNickname(user.getNickname());
        u.setFirstName(user.getFirstName());
        u.setLastName(user.getLastName());
        u.setPassword(user.getPassword());
        u.setEmail(user.getEmail());
        u.setBirthday(user.getBirthday());
        u.setAdministrator(user.isAdministrator());

        u.setRoles(
                user.getRoles().stream().
                        map(Converters::createRole).
                            collect(Collectors.toCollection(HashSet<RoleDTO>::new))
                  );
        u.setSent(
                user.getSent().stream().
                        map(Converters::createMessage).
                            collect(Collectors.toCollection(HashSet<MessageDTO>::new))
                 );
        u.setReceived(user.getReceived().stream().
                              map(Converters::createMessage).
                                  collect(Collectors.toCollection(HashSet<MessageDTO>::new))
                     );
        return u;
    }
}
