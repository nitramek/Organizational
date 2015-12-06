package cz.nitramek.organizational.data.implementation.util;

import cz.nitramek.organizational.data.implementation.dto.*;
import cz.nitramek.organizational.domain.classes.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

public class Converters {
    public static User convert(UserDTO userDTO) {
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
                        map(Converters::convert).
                               collect(Collectors.toCollection(ArrayList<Role>::new))
                  );
        u.setSent(
                userDTO.getSent().stream().
                        map(Converters::convert).
                               collect(Collectors.toCollection(ArrayList<Message>::new))
                 );
        u.setReceived(userDTO.getReceived().stream().
                              map(Converters::convert).
                                     collect(Collectors.toCollection(ArrayList<Message>::new))
                     );
        return u;
    }

    public static Message convert(MessageDTO messageDTO) {
        Message m = new Message(messageDTO.getSubject());
        m.setId(messageDTO.getId());
        m.setDateSend(messageDTO.getDateSend());
        m.setText(messageDTO.getText());
        return m;
    }

    public static MessageDTO convert(Message message) {
        MessageDTO m = new MessageDTO();
        m.setSubject(message.getSubject());
        m.setId(message.getId());
        m.setDateSend(message.getDateSend());
        m.setText(message.getText());
        return m;
    }

    public static Role convert(RoleDTO roleDTO) {
        Role r = new Role();
        r.setId(roleDTO.getId());
        r.setName(roleDTO.getName());
        r.setDisplayName(roleDTO.getDisplayName());
        r.setPermissions(
                roleDTO.getPermission().stream().
                        map(Converters::convert).
                               collect(Collectors.toCollection(ArrayList<Permission>::new))
                        );
        return r;
    }

    public static RoleDTO convert(Role role) {
        RoleDTO r = new RoleDTO();
        r.setId(role.getId());
        r.setName(role.getName());
        r.setDisplayName(role.getDisplayName());
        r.setPermissions(
                role.getPermissions().stream().
                        map(Converters::convert).
                            collect(Collectors.toCollection(HashSet<PermissionDTO>::new))
                        );
        return r;
    }

    private static PermissionDTO convert(Permission permission) {
        PermissionDTO p = new PermissionDTO();
        p.setId(permission.getId());
        p.setLevel(permission.getLevel());
        return p;
    }


    private static Permission convert(PermissionDTO permissionDTO) {
        Permission p = new Permission(permissionDTO.getLevel());
        p.setId(permissionDTO.getId());
        p.setLevel(permissionDTO.getLevel());
        return p;
    }

    public static UserDTO convert(User user) {
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
                        map(Converters::convert).
                            collect(Collectors.toCollection(HashSet<RoleDTO>::new))
                  );
        u.setSent(
                user.getSent().stream().
                        map(Converters::convert).
                            collect(Collectors.toCollection(HashSet<MessageDTO>::new))
                 );
        u.setReceived(user.getReceived().stream().
                              map(Converters::convert).
                                  collect(Collectors.toCollection(HashSet<MessageDTO>::new))
                     );
        return u;
    }

    public static ItemType convert(ItemTypeDTO itemTypeDTO) {
        ItemType i = new ItemType(itemTypeDTO.getName());
        i.setId(itemTypeDTO.getId());
        i.setAttributeTypes(
                itemTypeDTO.getAttributeTypes().stream().
                        map(Converters::convert).
                                   collect(Collectors.toCollection(ArrayList<AttributeType>::new))
                           );
        return i;

    }

    public static AttributeType convert(AttributeTypeDTO attributeTypeDTO) {
        AttributeType at = new AttributeType(
                attributeTypeDTO.isMandatory(),
                attributeTypeDTO.getName(),
                Converters.convert(attributeTypeDTO.getType())
        );
        at.setId(attributeTypeDTO.getId());
        return at;
    }

    public static AttributeValueType convert(AttributeValueTypeDTO type) {
        try {
            AttributeValueType avt = new AttributeValueType(type.getMethodName(), type.getConvertingClass());
            avt.setId(type.getId());
            avt.setName(type.getName());
            return avt;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ItemTypeDTO convert(ItemType itemType) {
        ItemTypeDTO i = new ItemTypeDTO();
        i.setName(itemType.getName());
        i.setId(itemType.getId());
        i.setAttributeTypes(
                itemType.getAttributeTypes().stream().
                        map(Converters::convert).
                                collect(Collectors.toCollection(ArrayList<AttributeTypeDTO>::new))
                           );
        return i;

    }

    public static AttributeTypeDTO convert(AttributeType attributeType) {
        AttributeTypeDTO at = new AttributeTypeDTO();
        at.setId(attributeType.getId());
        at.setName(attributeType.getName());
        at.setMandatory(attributeType.isMandatory());
        at.setType(Converters.convert(attributeType.getType()));
        return at;
    }

    public static AttributeValueTypeDTO convert(AttributeValueType type) {

        try {
            AttributeValueTypeDTO avt = new AttributeValueTypeDTO();
            avt.setId(type.getId());
            avt.setName(type.getName());
            avt.setConvertingClass(type.getConvertingClass());
            avt.setMethodName(type.getMethodName());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return null;
    }


}
