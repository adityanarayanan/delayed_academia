;;; package -- Make Emacs work just the way I want it, no external dependencies here
;;; Commentary:
;;; Code:

;; Start Emacs in full screen mode and map to F11 key
(defun switch-fullscreen nil
  (interactive)
  (let* ((modes '(nil fullboth fullwidth fullheight))
         (cm (cdr (assoc 'fullscreen (frame-parameters) ) ) )
         (next (cadr (member cm modes) ) ) )
    (modify-frame-parameters
     (selected-frame)
     (list (cons 'fullscreen next)))))
(define-key global-map [f11] 'switch-fullscreen)

;; Enabling IDO to supercharge buffer and file interactions
(require 'ido)
(ido-mode t)
(setq ido-enable-flex-matching t)

;; Remove default startup buffer
(setq inhibit-startup-message t)

;; Get rid of annoying ~ files
(setq make-backup-files nil)

;; Show line numbers and add space after the number
(global-linum-mode t)
(setq linum-format "%d ")

;; Disable menu bar and toolbar
(menu-bar-mode -1)
(tool-bar-mode -1)

(provide 'init_functionality)
;;; init_functionality ends here
