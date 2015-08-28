/*
 * Copyright (c) 2011-2014 Julien Nicoulaud <julien.nicoulaud@gmail.com>
 * Copyright (c) 2015-2015 Vladimir Schneider <vladimir.schneider@gmail.com>
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.vladsch.idea.multimarkdown.editor;

import com.intellij.openapi.fileEditor.*;
import com.intellij.openapi.project.PossiblyDumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.vladsch.idea.multimarkdown.MultiMarkdownLanguage;
import com.vladsch.idea.multimarkdown.MultiMarkdownFileType;
import com.vladsch.idea.multimarkdown.settings.MultiMarkdownGlobalSettings;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;

public class MultiMarkdownHtmlEditorProvider implements FileEditorProvider, PossiblyDumbAware {

    public static final String EDITOR_TYPE_ID = MultiMarkdownLanguage.NAME + "HtmlEditor";

    public boolean accept(@NotNull Project project, @NotNull VirtualFile file) {
        return MultiMarkdownGlobalSettings.getInstance().showHtmlText.getValue()
                && (MultiMarkdownPreviewEditorProvider.accept(file));
    }

    @NotNull
    public FileEditor createEditor(@NotNull Project project, @NotNull VirtualFile file) {
        return new MultiMarkdownPreviewEditor(project, FileDocumentManager.getInstance().getDocument(file), true);
    }

    public void disposeEditor(@NotNull FileEditor editor) {
        editor.dispose();
    }

    @NotNull
    public FileEditorState readState(@NotNull Element sourceElement, @NotNull Project project, @NotNull VirtualFile file) {
        return FileEditorState.INSTANCE;
    }

    public void writeState(@NotNull FileEditorState state, @NotNull Project project, @NotNull Element targetElement) {
    }

    @NotNull
    public String getEditorTypeId() {
        return EDITOR_TYPE_ID;
    }

    @NotNull
    public FileEditorPolicy getPolicy() {
        return FileEditorPolicy.PLACE_AFTER_DEFAULT_EDITOR;
    }

    /**
     * Indicates the editor can be created while background indexing is running.
     *
     * @return {@code true}
     */
    @Override
    public boolean isDumbAware() {
        return true;
    }
}
