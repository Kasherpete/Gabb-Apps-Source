package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.reflect.TypeToken;

public final class JsonAdapterAnnotationTypeAdapterFactory implements TypeAdapterFactory {
    private final ConstructorConstructor constructorConstructor;

    public JsonAdapterAnnotationTypeAdapterFactory(ConstructorConstructor constructorConstructor2) {
        this.constructorConstructor = constructorConstructor2;
    }

    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        JsonAdapter jsonAdapter = (JsonAdapter) typeToken.getRawType().getAnnotation(JsonAdapter.class);
        if (jsonAdapter == null) {
            return null;
        }
        return getTypeAdapter(this.constructorConstructor, gson, typeToken, jsonAdapter);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v5, resolved type: com.google.gson.internal.bind.TreeTypeAdapter} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v16, resolved type: com.google.gson.TypeAdapter<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v17, resolved type: com.google.gson.TypeAdapter} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: com.google.gson.internal.bind.TreeTypeAdapter} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v18, resolved type: com.google.gson.internal.bind.TreeTypeAdapter} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v19, resolved type: com.google.gson.internal.bind.TreeTypeAdapter} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.gson.TypeAdapter<?> getTypeAdapter(com.google.gson.internal.ConstructorConstructor r8, com.google.gson.Gson r9, com.google.gson.reflect.TypeToken<?> r10, com.google.gson.annotations.JsonAdapter r11) {
        /*
            r7 = this;
            java.lang.Class r7 = r11.value()
            com.google.gson.reflect.TypeToken r7 = com.google.gson.reflect.TypeToken.get(r7)
            com.google.gson.internal.ObjectConstructor r7 = r8.get(r7)
            java.lang.Object r7 = r7.construct()
            boolean r8 = r7 instanceof com.google.gson.TypeAdapter
            if (r8 == 0) goto L_0x0017
            com.google.gson.TypeAdapter r7 = (com.google.gson.TypeAdapter) r7
            goto L_0x007a
        L_0x0017:
            boolean r8 = r7 instanceof com.google.gson.TypeAdapterFactory
            if (r8 == 0) goto L_0x0022
            com.google.gson.TypeAdapterFactory r7 = (com.google.gson.TypeAdapterFactory) r7
            com.google.gson.TypeAdapter r7 = r7.create(r9, r10)
            goto L_0x007a
        L_0x0022:
            boolean r8 = r7 instanceof com.google.gson.JsonSerializer
            if (r8 != 0) goto L_0x0060
            boolean r0 = r7 instanceof com.google.gson.JsonDeserializer
            if (r0 == 0) goto L_0x002b
            goto L_0x0060
        L_0x002b:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r11 = "Invalid attempt to bind an instance of "
            java.lang.StringBuilder r9 = r9.append(r11)
            java.lang.Class r7 = r7.getClass()
            java.lang.String r7 = r7.getName()
            java.lang.StringBuilder r7 = r9.append(r7)
            java.lang.String r9 = " as a @JsonAdapter for "
            java.lang.StringBuilder r7 = r7.append(r9)
            java.lang.String r9 = r10.toString()
            java.lang.StringBuilder r7 = r7.append(r9)
            java.lang.String r9 = ". @JsonAdapter value must be a TypeAdapter, TypeAdapterFactory, JsonSerializer or JsonDeserializer."
            java.lang.StringBuilder r7 = r7.append(r9)
            java.lang.String r7 = r7.toString()
            r8.<init>(r7)
            throw r8
        L_0x0060:
            r0 = 0
            if (r8 == 0) goto L_0x0068
            r8 = r7
            com.google.gson.JsonSerializer r8 = (com.google.gson.JsonSerializer) r8
            r2 = r8
            goto L_0x0069
        L_0x0068:
            r2 = r0
        L_0x0069:
            boolean r8 = r7 instanceof com.google.gson.JsonDeserializer
            if (r8 == 0) goto L_0x0070
            r0 = r7
            com.google.gson.JsonDeserializer r0 = (com.google.gson.JsonDeserializer) r0
        L_0x0070:
            r3 = r0
            com.google.gson.internal.bind.TreeTypeAdapter r7 = new com.google.gson.internal.bind.TreeTypeAdapter
            r6 = 0
            r1 = r7
            r4 = r9
            r5 = r10
            r1.<init>(r2, r3, r4, r5, r6)
        L_0x007a:
            if (r7 == 0) goto L_0x0086
            boolean r8 = r11.nullSafe()
            if (r8 == 0) goto L_0x0086
            com.google.gson.TypeAdapter r7 = r7.nullSafe()
        L_0x0086:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory.getTypeAdapter(com.google.gson.internal.ConstructorConstructor, com.google.gson.Gson, com.google.gson.reflect.TypeToken, com.google.gson.annotations.JsonAdapter):com.google.gson.TypeAdapter");
    }
}
