package cz.nitramek.organizational.data.implementation.util;

import cz.nitramek.organizational.data.implementation.dto.*;
import cz.nitramek.organizational.domain.classes.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

public class Converters {
    public static User convert(UserDTO userDTO) {
        User u = null;
        if (userDTO != null) {
            u = new User();
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
        }
        return u;
    }

    public static Message convert(MessageDTO messageDTO) {
        Message m = null;
        if (messageDTO != null) {
            m = new Message(messageDTO.getSubject());
            if (messageDTO instanceof BorrowRequestDTO) {
                BorrowRequestDTO brDTO = (BorrowRequestDTO) messageDTO;
                m = new BorrowRequest(brDTO.getSubject(), Converters.convert(brDTO.getRequestedItemDTO()));
            }
            if (messageDTO instanceof NotificationDTO) {
                NotificationDTO nDTO = (NotificationDTO) messageDTO;
                Notification n = new Notification(messageDTO.getSubject());
                n.setTriggredItem(Converters.convert(nDTO.getTriggeredItemDTO()));
                n.setTriggeredValue(nDTO.getTriggeredValue());
                m = n;
            }

            m.setId(messageDTO.getId());
            m.setDateSend(messageDTO.getDateSend());
            m.setText(messageDTO.getText());
        }
        return m;
    }

    public static MessageDTO convert(Message message) {
        MessageDTO m = null;
        if (message != null) {
            m = new MessageDTO();
            if (message instanceof BorrowRequest) {
                BorrowRequestDTO brDTO = new BorrowRequestDTO();
                BorrowRequest br = (BorrowRequest) message;
                brDTO.setRequestedItemDTO(Converters.convert(br.getRequestedItem()));
                m = brDTO;
            }
            if (message instanceof Notification) {
                NotificationDTO nDTO = new NotificationDTO();
                Notification n = (Notification) message;
                nDTO.setTriggeredValue(nDTO.getTriggeredValue());
                nDTO.setTriggeredItemDTO(Converters.convert(n.getTriggredItem()));
                m = nDTO;
            }
            m.setSubject(message.getSubject());
            m.setId(message.getId());
            m.setDateSend(message.getDateSend());
            m.setText(message.getText());
        }
        return m;
    }

    public static Role convert(RoleDTO roleDTO) {
        Role r = null;
        if (roleDTO != null) {
            r = new Role();
            r.setId(roleDTO.getId());
            r.setName(roleDTO.getName());
            r.setDisplayName(roleDTO.getDisplayName());
            r.setPermissions(
                    roleDTO.getPermission().stream().
                            map(Converters::convert).
                                   collect(Collectors.toCollection(ArrayList<Permission>::new))
                            );
        }
        return r;
    }

    public static RoleDTO convert(Role role) {
        RoleDTO r = null;
        if (role != null) {
            r = new RoleDTO();
            r.setId(role.getId());
            r.setName(role.getName());
            r.setDisplayName(role.getDisplayName());
            r.setPermissions(
                    role.getPermissions().stream().
                            map(Converters::convert).
                                collect(Collectors.toCollection(HashSet<PermissionDTO>::new))
                            );
        }
        return r;
    }

    private static PermissionDTO convert(Permission permission) {
        PermissionDTO p = null;
        if (permission != null) {
            p = new PermissionDTO();
            p.setId(permission.getId());
            p.setLevel(permission.getLevel());
        }
        return p;
    }


    private static Permission convert(PermissionDTO permissionDTO) {
        Permission p = null;
        if (permissionDTO != null) {
            p = new Permission(permissionDTO.getLevel());
            p.setId(permissionDTO.getId());
            p.setLevel(permissionDTO.getLevel());
        }
        return p;
    }

    public static UserDTO convert(User user) {
        UserDTO u = null;
        if (user != null) {
            u = new UserDTO();
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
        }
        return u;
    }

    public static ItemType convert(ItemTypeDTO itemTypeDTO) {
        ItemType i = null;
        if (itemTypeDTO != null) {
            i = new ItemType(itemTypeDTO.getName());
            i.setId(itemTypeDTO.getId());
            i.setAttributeTypes(
                    itemTypeDTO.getAttributeTypes().stream().
                            map(Converters::convert).
                                       collect(Collectors.toCollection(ArrayList<AttributeType>::new))
                               );
        }
        return i;

    }

    public static AttributeType convert(AttributeTypeDTO attributeTypeDTO) {
        AttributeType at = null;
        if (attributeTypeDTO != null) {
            at = new AttributeType(
                    attributeTypeDTO.isMandatory(),
                    attributeTypeDTO.getName(),
                    Converters.convert(attributeTypeDTO.getType())
            );
            at.setId(attributeTypeDTO.getId());
        }
        return at;
    }

    public static AttributeValueType convert(AttributeValueTypeDTO type) {
        if (type != null) {
            try {
                AttributeValueType avt = new AttributeValueType(type.getMethodName(), type.getConvertingClass());
                avt.setId(type.getId());
                avt.setName(type.getName());
                return avt;
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static ItemTypeDTO convert(ItemType itemType) {
        ItemTypeDTO i = null;
        if (itemType != null) {
            i = new ItemTypeDTO();
            i.setName(itemType.getName());
            i.setId(itemType.getId());
            i.setAttributeTypes(
                    itemType.getAttributeTypes().stream().
                            map(Converters::convert).
                                    collect(Collectors.toCollection(ArrayList<AttributeTypeDTO>::new))
                               );
        }
        return i;

    }

    public static AttributeTypeDTO convert(AttributeType attributeType) {
        AttributeTypeDTO at = null;
        if (attributeType != null) {
            at = new AttributeTypeDTO();
            at.setId(attributeType.getId());
            at.setName(attributeType.getName());
            at.setMandatory(attributeType.isMandatory());
            at.setType(Converters.convert(attributeType.getType()));
        }
        return at;
    }

    public static AttributeValueTypeDTO convert(AttributeValueType type) {

        if (type != null) {
            try {
                AttributeValueTypeDTO avt = new AttributeValueTypeDTO();
                avt.setId(type.getId());
                avt.setName(type.getName());
                avt.setConvertingClass(type.getConvertingClass());
                avt.setMethodName(type.getMethodName());
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }

        return null;
    }


    public static Item convert(ItemDTO itemDTO) {
        Item i = null;
        if (itemDTO != null) {
            i = new Item();
            i.setId(itemDTO.getId());
            i.setName(itemDTO.getName());
            i.setBorrowable(itemDTO.isBorrowable());
            i.setDateAdded(itemDTO.getDateAdded());
            i.setDateChanged(itemDTO.getDateChanged());
            i.setType(Converters.convert(itemDTO.getType()));
            i.setOwner(Converters.convert(itemDTO.getOwner()));
            i.setPermissions(itemDTO.getPermissionDTOs().stream()
                                    .map(Converters::convert).collect(Collectors.toList()));
        }
        return i;
    }

    public static ItemDTO convert(Item item) {
        ItemDTO i = null;
        if (item != null) {
            i = new ItemDTO();
            i.setId(item.getId());
            i.setName(item.getName());
            i.setBorrowable(item.isBorrowable());
            i.setDateAdded(item.getDateAdded());
            i.setDateChanged(item.getDateChanged());
            i.setType(Converters.convert(item.getType()));
            i.setOwner(Converters.convert(item.getOwner()));
            i.setPermissionDTOs(item.getPermissions().stream()
                                    .map(Converters::convert).collect(Collectors.toList()));
            i.setAttributeDTOs(item.getAttributes().stream()
                                   .map(Converters::convert).collect(Collectors.toList()));
            i.setCategoryId(item.getCategory().getId());
        }
        return i;
    }


    public static NotificationSetting convert(NotificationSettingDTO notificationSettingDTO) {
        NotificationSetting ns = null;
        if (notificationSettingDTO != null) {
            ns = new NotificationSetting(
                    notificationSettingDTO.getOperation(),
                    notificationSettingDTO.getName(),
                    notificationSettingDTO.getTriggerValue(),
                    Converters.convert(notificationSettingDTO.getWatchedAttributeDTO())
            );
            if (notificationSettingDTO instanceof CompositeNotificationSettingDTO) {
                CompositeNotificationSettingDTO cnsDTO = (CompositeNotificationSettingDTO) notificationSettingDTO;
                CompositeNotificationSetting cns = new CompositeNotificationSetting(
                        ns.getOperation(),
                        ns.getName(),
                        ns.getTriggerValue(),
                        ns.getWatchedAttribute(),
                        cnsDTO.getCompositeOperation(),
                        Converters.convert(cnsDTO.getNextNotificationSettingDTO())
                );
                ns = cns;
            }
        }

        return ns;
    }


    public static NotificationSettingDTO convert(NotificationSetting notificationSetting) {
        NotificationSettingDTO ns = null;
        if (notificationSetting != null) {
            ns = new NotificationSettingDTO();
            if (notificationSetting instanceof CompositeNotificationSetting) {
                CompositeNotificationSetting cns = (CompositeNotificationSetting) notificationSetting;
                CompositeNotificationSettingDTO cnsDTO = new CompositeNotificationSettingDTO();
                cnsDTO.setCompositeOperation(cns.getCompositeOperation());
                cnsDTO.setNextNotificationSettingDTO(Converters.convert(cns.getNextNotificationSetting()));
            }
            ns.setId(notificationSetting.getId());
            ns.setName(notificationSetting.getName());
            ns.setOperation(notificationSetting.getOperation());
            ns.setText(notificationSetting.getText());
            ns.setTriggerValue(notificationSetting.getTriggerValue());
            ns.setWatchedAttributeDTO(Converters.convert(notificationSetting.getWatchedAttribute()));
            ns.setOwner(Converters.convert(notificationSetting.getUser()));
        }
        return ns;
    }

    private static AttributeDTO convert(Attribute attribute) {
        AttributeDTO aDTO = null;
        if (attribute != null) {
            aDTO = new AttributeDTO();
            aDTO.setId(attribute.getId());
            aDTO.setStrValue(attribute.getStrValue());
            aDTO.setType(Converters.convert(attribute.getType()));
        }

        return aDTO;
    }

    private static Attribute convert(AttributeDTO attributeDTO) {
        Attribute a = null;
        if (attributeDTO != null) {
            a = new Attribute(attributeDTO.getStrValue(), Converters.convert(attributeDTO.getType()));
            a.setId(attributeDTO.getId());
        }
        return a;
    }

}
