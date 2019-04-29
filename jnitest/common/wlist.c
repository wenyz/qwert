//
// Created by wenyz on 2019/3/4.
//

#include <stdio.h>
#include <malloc.h>
#include "wlist.h"

list *listCreate(void) {

    struct list *list;
    if ((list = malloc(sizeof(*list))) == NULL)
        return NULL;
    list->head = list->tail = NULL;
    list->len = 0;
    list->dup = NULL;
    list->free = NULL;
    list->match = NULL;
    return list;
}

void listEmpty(list *list) {
    unsigned long len;
    listNode *current, *next;

    len = list->len;
    current = list->head;

    while (len--) {
        next = current->next;
        if (list->free) list->free(current->value);
        free(current);
        current = next;
    }
    list->head = list->tail = NULL;
    list->len = 0;
}

void listRelease(list *list) {
    listEmpty(list);
    free(list);
}

list *listAddNodeHead(list *list, void *value) {

    listNode *node;
    if ((node = malloc(sizeof(*node))) == NULL)
        return NULL;
    node->value = value;
    if (list->len == 0) {
        list->head = list->tail = node;
        node->prev = node->next = NULL;
    } else {
        list->head->prev = node;
        node->next = list->head;
        node->prev = NULL;
        list->head = node;
    }

    list->len++;
    return list;
}

list *listAddNodeTail(list *list, void *value) {

    listNode *node;

    if ((node = malloc(sizeof(*node))) == NULL)
        return NULL;
    node->value = value;

    if (list->len == 0) {
        list->head = list->tail = node;
        node->prev = node->next = NULL;
    } else {
        node->prev = list->tail;
        node->next = NULL;
        list->tail->next = node;
        list->tail = node;
    }

    list->len++;
    return list;
}

// you wenti ?
// budong
list *listInsertNode(list *list, listNode *old_node, void *value, int after) {

    listNode *node;
    if ((node = malloc(sizeof(*node))) == NULL)
        return NULL;
    node->value = value;

    if (after) {
        node->prev = old_node;
        node->next = old_node->next;
        //old_node->next = node; //?
        if (list->tail == old_node) {
            list->tail = node;
        }
    } else {
        node->next = old_node->next;
        old_node->next = node;
        // node->prev = old_node; //?
        if (list->head == old_node) {
            list->head = node;
        }
    }

    if (node->prev != NULL) {
        node->prev->next = node;
    }
    if (node->next != NULL) {
        node->next->prev = node;
    }

    list->len++;
    return list;

}

void listDelNode(list *list, listNode *node) {

    if (node->prev)
        node->prev->next = node->next;
    else
        list->head = node->next;
    if (node->next)
        node->next->prev = node->prev;
    else
        list->tail = node->prev;

    if (list->free) list->free(node->value);
    free(node);
    list->len--;
}

listIter *listGetIterator(list *list, int direction) {

    listIter *iter;

    if ((iter = malloc(sizeof(*iter))) == NULL)
        return NULL;
    if (direction == AL_START_HEAD)
        iter->next = list->head;
    else
        iter->next = list->tail;
    iter->direction = direction;
    return iter;
}

void listReleaseIterator(listIter *iter){
    free(iter);
}

void listRewind(list *list,listIter *li){
    li->next = list->head;
    li->direction=AL_START_HEAD;
}

void listRewindTail(list *list,listIter *li){
    li->next = list->head;
    li->direction = AL_START_TAIL;
}

listNode *listNext(listIter *iter){

    listNode *current = iter->next;
    if(current !=NULL){
        if(iter->direction ==AL_START_HEAD)
            iter->next = current->next;
        else
            iter->next = current->prev;
    }

    return current;
}

list *listDup(list *orig){

    list *copy;
    listIter iter;
    listNode *node;

    if((copy = listCreate()) == NULL)
        return NULL;

    copy->dup = orig->dup;
    copy->free = orig->free;
    copy->match = orig->match;
    listRewind(orig,&iter);

    while ((node = listNext(&iter)) != NULL){
        void * value;
        if(copy->dup){
            value = copy->dup(node->value);
            if(value == NULL){
                listRelease(copy);
                return NULL;
            }
        }else
            value = node->value;
        if(listAddNodeTail(copy,value) == NULL){
            listRelease(copy);
            return NULL;
        }
    }
    return copy;
}

listNode *listSearchKey(list *list,void *key){

    listIter iter;
    listNode *node;

    listRewind(list,&iter);
    while((node = listNext(&iter)) !=NULL){

        if(list->match){
            if(list->match(node->value,key)){
                return node;
            }
        } else{
            if(node->value == key){
                return node;
            }
        }
    }
    return NULL;
}


listNode *listIndex(list *list,long index){

    listNode *n;

    if(index<0){
        index = (-index)-1;
        n=list->tail;
        while (index-- && n) n = n->prev;
    } else{
        n=list->head;
        while (index-- && n) n = n->next;
    }
    return n;
}

void listRotate(list *list){
    listNode *tail  = list->tail;
    if(list->len <=1) return;

    list->tail = tail->prev;
    list->tail->next = NULL;

    list->head->prev = tail;
    tail->prev = NULL;
    tail->next = list->head;
    list->head = tail;
}

void listJoin(list *l,list *o){
    if (o->head)
        o->head->prev = l->tail;

    if (l->tail)
        l->tail->next = o->head;
    else
        l->head = o->head;

    if (o->tail) l->tail = o->tail;
    l->len += o->len;

    /* Setup other as an empty list. */
    o->head = o->tail = NULL;
    o->len = 0;
}




int main() {
    struct list *list;
//    list = listCreate();
//    struct list *list1, *list2, *list3, *list4, *list5, *list6;
//    listAddNodeHead(list, list1);
//    listAddNodeTail(list, list3);
//    listAddNodeTail(list, list4);
//    listAddNodeTail(list, list5);
//    listAddNodeTail(list, list6);
//
//    listInsertNode(list, &list3, list2, 1);

    struct list eeee;

    printf("length is %d ", sizeof(list));
    printf("length is %d ", sizeof(eeee));
}